package io.github.antonpichka.lamm

fun debugPrint(text: String) {
    println(text)
}

fun debugPrintException(text: String) {
    debugPrint("\u001B[31m$text\u001B[0m")
}