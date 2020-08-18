package lyl.manci.customcreditcard

import android.text.Editable


/**       Code with ❤  ´• ل •`   ❤
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
▬     Created by Leyla Akmancı                ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬    ▬
▬     leyla.manci@gmail.com                           ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬     ▬
▬     18/08/2020 - 17:08        ▬
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
 */
fun isInputCorrect(
    s: Editable,
    size: Int,
    dividerPosition: Int,
    divider: Char
): Boolean {
    var isCorrect = (s.length <= size)
    (0 until s.length).forEach { i ->
        isCorrect = if (i > 0 && ((i + 1) % dividerPosition == 0)) {
            isCorrect && (divider == s[i])
        } else {
            isCorrect && s[i].isDigit()
        }
    }
    return isCorrect
}

fun concatString(
    digits: CharArray,
    dividerPosition: Int,
    divider: Char
): String {
    val formatted = StringBuilder()
    for (i in 0 until digits.size) {
        if (digits[i] != '0') {
            formatted.append(digits[i])
            if ((i > 0) && (i < (digits.size - 1)) && (((i + 1) % dividerPosition) == 0)) {
                formatted.append(divider)
            }
        }
    }

    return formatted.toString()
}

fun getDigitArray(
    s: Editable,
    size: Int
)
        : CharArray {
    var digits = CharArray(size)
    var index = 0
    var i = 0
    while (i < s.length && index < size) {
        var current = s[i]
        if (current.isDigit()) {
            digits[index] = current
            index++
        }
        i++
    }
    return digits
}