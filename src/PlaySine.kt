import org.lwjgl.glfw.GLFW

var window: Long = 0
var freqs: Array<Float> = Array(32) { 0f }

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
    window = GLFW.glfwCreateWindow(300, 300, "Hello World!", 0, 0)
    GLFW.glfwShowWindow(window)
}

val keyMap = arrayOf(
        Pair(arrayOf(GLFW.GLFW_KEY_1), 440f * Math.pow(2.0, -3.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_Q), 440f * Math.pow(2.0, -2.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_2), 440f * Math.pow(2.0, -1.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_Z, GLFW.GLFW_KEY_W), 440f),
        Pair(arrayOf(GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_3), 440f * Math.pow(2.0, 1.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_X, GLFW.GLFW_KEY_E), 440f * Math.pow(2.0, 2.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_4), 440f * Math.pow(2.0, 3.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_C, GLFW.GLFW_KEY_R), 440f * Math.pow(2.0, 4.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_F, GLFW.GLFW_KEY_5), 440f * Math.pow(2.0, 5.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_V, GLFW.GLFW_KEY_T), 440f * Math.pow(2.0, 6.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_G, GLFW.GLFW_KEY_6), 440f * Math.pow(2.0, 7.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_B, GLFW.GLFW_KEY_Y), 440f * Math.pow(2.0, 8.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_H, GLFW.GLFW_KEY_7), 440f * Math.pow(2.0, 9.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_N, GLFW.GLFW_KEY_U), 440f * Math.pow(2.0, 10.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_J, GLFW.GLFW_KEY_8), 440f * Math.pow(2.0, 11.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_M, GLFW.GLFW_KEY_I), 440f * Math.pow(2.0, 12.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_K, GLFW.GLFW_KEY_9), 440f * Math.pow(2.0, 13.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_COMMA, GLFW.GLFW_KEY_O), 440f * Math.pow(2.0, 14.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_L, GLFW.GLFW_KEY_0), 440f * Math.pow(2.0, 15.0 / 12).toFloat()),
        Pair(arrayOf(GLFW.GLFW_KEY_PERIOD, GLFW.GLFW_KEY_P), 440f * Math.pow(2.0, 16.0 / 12).toFloat()))

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
