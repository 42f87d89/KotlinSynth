import org.lwjgl.glfw.GLFW
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine

class Synth// select audio format parameters
(sampleRate: Float, sampleSize: Int, channels: Int, bufferSize: Int) {
    private var t = 0 // time in frames
    private val af = AudioFormat(sampleRate, sampleSize, channels, true, false)
    private val line = AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, af)) as SourceDataLine
    private val notes = MutableList(10) {}
    private val b = ByteArray(bufferSize)
    val bufferTime = bufferSize*sampleRate

    init {
        GLFW.glfwSetTime(0.0)
        // prepare audio output
        line.open(af, bufferSize)
        line.start()
    }

    inline fun freq(x: Int): Float {
        val base = 220f
        return base * Math.pow(2.0, (x+4).toDouble() / 12).toFloat()
    }

    fun write() {
        val available = line.available()
        for (i in 0 until available) {
            b[i] = (generate(220f, t*af.sampleRate)*100).toByte()
            t++
        }
        line.write(b, 0, available)
    }

    fun generate(frequency: Float, time: Float): Double {
        val angle = Math.PI * frequency * time
        var result = Math.sin(angle)
        for (i in IntRange(2, 5)) {
            result += Math.sin(angle/(i.toDouble()))/(i.toDouble())
        }
        return result
    }

    fun close() {
        line.drain()
        line.stop()
        line.close()
    }

    fun notePlayed(x: Any, y: Any) {

    }

    fun noteReleased(x: Any, y: Any) {

    }
}