package extensions

import java.util.*

val String.lastChar: Char
    get() = this[length - 1]

fun main (){
    val str = "sdfvfgnb ghmn jk6754rf4fgc"
    println(str.evenChars())
    println(str.oddChars())
    println(str.upperEven())
    println(str.upperOdd())
    println(str.toBase64())
    println(str.lastChar)
}

fun String.evenChars(): String{
    return filterIndexed { index, _ -> index % 2 == 0 }
}

fun String.oddChars(): String{
    var result = "";
    for (index in 0 until this.chars().count()){
        if (index % 2 != 0L) {
            result+=this[index.toInt()];
        }
    }

    return result;
}

fun String.upperEven():String{
    var result = ""
    for (index in 0 until this.chars().count()){
        if (index % 2 == 0L) {
            result+=this[index.toInt()].uppercaseChar()
        } else {
            result+=this[index.toInt()].lowercaseChar()
        }
    }

    return result;
}

fun String.upperOdd():String{
    var result = ""
    for (index in 0 until this.chars().count()){
        if (index % 2 == 0L) {
            result+=this[index.toInt()].lowercaseChar()
        } else {
            result+=this[index.toInt()].uppercaseChar();
        }
    }

    return result;
}

fun String.toBase64():String{
    return Base64.getEncoder().encodeToString(this.toByteArray());
}