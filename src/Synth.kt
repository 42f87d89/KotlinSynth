import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine

class Synth// select audio format parameters
(sampleRate: Float, sampleSize: Int, channels: Int, bufferSize: Int) {
    private var t = 0
    private val af = AudioFormat(sampleRate, sampleSize, channels, true, false)
    private val buffer = ByteArray(bufferSize)
    private val line = AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, af)) as SourceDataLine

    init {
        // prepare audio output
        line.open(af, buffer.size * 64)
        line.start()
    }

    fun write(frequencies: Array<Float>, generator: (Float, Double) -> Double) {
        for (i in 0 until buffer.size) {
            val s = frequencies.sumBy { Math.round(generator(it, t / af.sampleRate.toDouble()) * 32).toInt() };
            buffer[i] = s.toByte()
            t++
        }
    }

    fun generateSine(frequency: Float, time: Double): Double {
        val angle = Math.PI * frequency * time
        return Math.sin(angle)
    }

    fun play() {
        line.write(buffer, 0, buffer.size)
    }

    fun close() {
        // shut down audio
        line.drain()
        line.stop()
        line.close()
    }
}