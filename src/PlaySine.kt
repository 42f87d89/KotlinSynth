import org.lwjgl.glfw.GLFW

var window: Long = 0
var freqs: Array<Float> = Array(32){0f}

fun main(args: Array<String>) {
    initWindow()
    val s = Synth(44100f, 16, 1, 64)
    while (!GLFW.glfwWindowShouldClose(window)) {
        GLFW.glfwPollEvents()
        getInputs()
        s.write(freqs){ x, y -> s.generateSine(x,y)}
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
        Pair(GLFW.GLFW_KEY_Z, 440f),
        Pair(GLFW.GLFW_KEY_S, 440f*Math.pow(2.0,1.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_X, 440f*Math.pow(2.0,2.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_D, 440f*Math.pow(2.0,3.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_C, 440f*Math.pow(2.0,4.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_F, 440f*Math.pow(2.0,5.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_V, 440f*Math.pow(2.0,6.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_G, 440f*Math.pow(2.0,7.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_B, 440f*Math.pow(2.0,8.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_H, 440f*Math.pow(2.0,9.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_N, 440f*Math.pow(2.0,10.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_J, 440f*Math.pow(2.0,11.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_M, 440f*Math.pow(2.0,12.0/12).toFloat()),
        Pair(GLFW.GLFW_KEY_K, 440f*Math.pow(2.0,13.0/12).toFloat()))

fun getInputs() {
    for ((i, e) in keyMap.withIndex()) {
        val key = GLFW.glfwGetKey(window, e.first)
        if(key == 1) {
            freqs[i] = e.second
        } else if (key == 0) {
            freqs[i] = 0f
        }
    }
}
