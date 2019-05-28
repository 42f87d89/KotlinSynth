import org.lwjgl.glfw.GLFW

var window: Long = 0
val s = Synth(44100f, 16, 1, 64)

fun main(args: Array<String>) {
    initWindow()
    while (!GLFW.glfwWindowShouldClose(window)) {
        GLFW.glfwWaitEventsTimeout(s.bufferTime.toDouble()/2)
        GLFW.glfwPollEvents()
        s.write()
    }
}

fun initWindow() {
    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (!GLFW.glfwInit())
        throw IllegalStateException("Unable to initialize GLFW")

    // Create the window
    window = GLFW.glfwCreateWindow(300, 300, "Hit Some Keys!", 0, 0)

    GLFW.glfwSetKeyCallback(window) { _: Long, k: Int, _: Int, ac: Int, _: Int ->
        if (ac == GLFW.GLFW_REPEAT) return@glfwSetKeyCallback
        keyCallback(k, ac)
    }
    GLFW.glfwShowWindow(window)
}

fun keyCallback(k: Int, ac: Int) {
    if (k == GLFW.GLFW_KEY_ESCAPE) {
        s.close()
        GLFW.glfwSetWindowShouldClose(window, true)
    }
    var x = -1
    var y = -1
    var found = false


    for (col in 0 until keyboard.size) {
        for (row in 0 until keyboard[col].size) {
            if(keyboard[col][row] == k) {
                y = row
                found = true
                break
            }
        }
        if (found) {
            x = col
            break
        }
    }
    if (found) {
        if (ac == GLFW.GLFW_PRESS)
            s.notePlayed(x, y)
        else if (ac == GLFW.GLFW_RELEASE)
            s.noteReleased(x, y)
    }
}

val keyboard = arrayOf(
        arrayOf(
                GLFW.GLFW_KEY_BACKSLASH,
                GLFW.GLFW_KEY_A,
                GLFW.GLFW_KEY_W,
                GLFW.GLFW_KEY_3),
        arrayOf(
                GLFW.GLFW_KEY_Z,
                GLFW.GLFW_KEY_S,
                GLFW.GLFW_KEY_E,
                GLFW.GLFW_KEY_4),
        arrayOf(
                GLFW.GLFW_KEY_X,
                GLFW.GLFW_KEY_D,
                GLFW.GLFW_KEY_R,
                GLFW.GLFW_KEY_5),
        arrayOf(
                GLFW.GLFW_KEY_C,
                GLFW.GLFW_KEY_F,
                GLFW.GLFW_KEY_T,
                GLFW.GLFW_KEY_6),
        arrayOf(
                GLFW.GLFW_KEY_V,
                GLFW.GLFW_KEY_G,
                GLFW.GLFW_KEY_Y,
                GLFW.GLFW_KEY_7),
        arrayOf(
                GLFW.GLFW_KEY_B,
                GLFW.GLFW_KEY_H,
                GLFW.GLFW_KEY_U,
                GLFW.GLFW_KEY_8),
        arrayOf(
                GLFW.GLFW_KEY_N,
                GLFW.GLFW_KEY_J,
                GLFW.GLFW_KEY_I,
                GLFW.GLFW_KEY_9),
        arrayOf(
                GLFW.GLFW_KEY_M,
                GLFW.GLFW_KEY_K,
                GLFW.GLFW_KEY_O,
                GLFW.GLFW_KEY_0),
        arrayOf(
                GLFW.GLFW_KEY_COMMA,
                GLFW.GLFW_KEY_L,
                GLFW.GLFW_KEY_P,
                GLFW.GLFW_KEY_MINUS),
        arrayOf(
                GLFW.GLFW_KEY_PERIOD,
                GLFW.GLFW_KEY_SEMICOLON,
                GLFW.GLFW_KEY_LEFT_BRACKET,
                GLFW.GLFW_KEY_EQUAL),
        arrayOf(
                GLFW.GLFW_KEY_SLASH,
                GLFW.GLFW_KEY_APOSTROPHE,
                GLFW.GLFW_KEY_RIGHT_BRACKET))
