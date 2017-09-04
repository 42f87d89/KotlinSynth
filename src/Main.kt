import org.lwjgl.glfw.GLFW

var window: Long = 0
var freqs: Array<Float> = Array(64) { 0f }

fun main(args: Array<String>) {
    initWindow()
    val s = Synth(44100f, 16, 1, 64)
    while (!GLFW.glfwWindowShouldClose(window)) {
        GLFW.glfwPollEvents()
        getInputs()
        s.write(freqs) { x, y -> s.generateSine(x, y) }
        s.play()
    }
    s.close()
}

fun initWindow() {
    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (!GLFW.glfwInit())
        throw IllegalStateException("Unable to initialize GLFW")

    // Create the window
    window = GLFW.glfwCreateWindow(300, 300, "Hit Some Keys!", 0, 0)
    GLFW.glfwShowWindow(window)
}

val keyMap = arrayOf(
        Pair(arrayOf(GLFW.GLFW_KEY_A), 110f * Math.pow(2.0, 11.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_Z), 220f),
        Pair(arrayOf(GLFW.GLFW_KEY_S), 220f * Math.pow(2.0, 1.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_X), 220f * Math.pow(2.0, 2.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_D), 220f * Math.pow(2.0, 3.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_C), 220f * Math.pow(2.0, 4.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_F), 220f * Math.pow(2.0, 5.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_V), 220f * Math.pow(2.0, 6.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_G), 220f * Math.pow(2.0, 7.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_B), 220f * Math.pow(2.0, 8.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_1, GLFW.GLFW_KEY_H), 220f * Math.pow(2.0, 9.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_Q, GLFW.GLFW_KEY_N), 220f * Math.pow(2.0, 10.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_2, GLFW.GLFW_KEY_J), 220f * Math.pow(2.0, 11.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_M), 440f),
        Pair(arrayOf(GLFW.GLFW_KEY_3, GLFW.GLFW_KEY_K), 440f * Math.pow(2.0, 1.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_E, GLFW.GLFW_KEY_COMMA), 440f * Math.pow(2.0, 2.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_4, GLFW.GLFW_KEY_L), 440f * Math.pow(2.0, 3.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_R, GLFW.GLFW_KEY_PERIOD), 440f * Math.pow(2.0, 4.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_5), 440f * Math.pow(2.0, 5.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_T), 440f * Math.pow(2.0, 6.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_6), 440f * Math.pow(2.0, 7.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_Y), 440f * Math.pow(2.0, 8.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_7), 440f * Math.pow(2.0, 9.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_U), 440f * Math.pow(2.0, 10.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_8), 440f * Math.pow(2.0, 11.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_I), 880f),
        Pair(arrayOf(GLFW.GLFW_KEY_9), 880f * Math.pow(2.0, 1.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_O), 880f * Math.pow(2.0, 2.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_0), 880f * Math.pow(2.0, 3.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_P), 880f * Math.pow(2.0, 4.0 / 12).toFloat()))


fun getInputs() {
    for ((i, e) in keyMap.withIndex()) {
        var key = 0
        for (k in e.first) {
            if (GLFW.glfwGetKey(window, k) == 1) {
                key = 1
                break
            }
        }
        if (key == 1) {
            freqs[i] = e.second
        } else if (key == 0) {
            freqs[i] = 0f
        }
    }
}
