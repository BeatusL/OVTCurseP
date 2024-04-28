val tInit = mapOf(
    Pair('0', '0') to "0", Pair('0', '1') to "1",
    Pair('1', '0') to "1", Pair('1', '1') to "0"
)

val rsInitR = mapOf(
    Pair('0', '0') to "-", Pair('0', '1') to "0",
    Pair('1', '0') to "1", Pair('1', '1') to "0"
)

val rsInitS = mapOf(
    Pair('0', '0') to "0", Pair('0', '1') to "1",
    Pair('1', '0') to "0", Pair('1', '1') to "-"
)

val q = listOf("0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111")
val qInit00 = listOf("0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111")
val qInit01 = listOf("----", "----", "----", "----", "----", "----", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0110")
val qInit10 = listOf("0000", "0010", "0100", "0110", "1000", "1010", "1100", "1110", "0000", "0010", "0100", "0110", "1000", "1010", "1100", "1110")
val qInit11 = listOf("----", "----", "----", "----", "----", "----", "----", "----", "----", "----", "----", "----", "----", "----", "----", "----")

val qInit = listOf(qInit00, qInit01, qInit10, qInit11)
val xt = mutableMapOf<String, Array<Array<String>>>()
val xr = mutableMapOf<String, Array<Array<String>>>()
val xs = mutableMapOf<String, Array<Array<String>>>()

fun createTable() {
    for (x in listOf("00", "01", "10", "11")) {
        val nextQ = qInit[x.toInt(2)]
        val r = Array(q.size) { Array(4) { "" } }
        val s = Array(q.size) { Array(4) { "" } }
        val t = Array(q.size) { Array(4) { "" } }
        for (i in q.indices) {
            for (j in 0..3) {
                r[i][j] = if (nextQ[i] != "----") rsInitR[Pair(q[i][j], nextQ[i][j])]!! else "-"
                s[i][j] = if (nextQ[i] != "----") rsInitS[Pair(q[i][j], nextQ[i][j])]!! else "-"
                t[i][j] = if (nextQ[i] != "----") tInit[Pair(q[i][j], nextQ[i][j])]!! else "-"
            }
        }
        xt[x] = t
        xr[x] = r
        xs[x] = s

        println()
        println("Trigger init table for X = $x.")
        println(" Q      Q+1     R      S     T")
        for (i in q.indices) {
            print(q[i] + " | " + nextQ[i] + " | ")
            r[i].forEach { print(it) }
            print(" | ")
            s[i].forEach { print(it) }
            print(" | ")
            t[i].forEach { print(it) }
            print("\n")
        }
    }
}

fun qToI(q: String) = q.toInt(2)

fun createKM() {
    //  Q3 X0 X1 | Q2 Q1 Q0
    val cList = listOf("000", "001", "011", "010", "110", "111", "101", "100")
    // R
    for (k in 3 downTo 0) {
        print("Map for R${3-k}\n")
        for (i in cList.indices) { // h
            for (j in cList.indices) { // v
                val curX = cList[j][2].toString() + cList[j][1]
                val curQI = qToI(cList[j][0].toString() + cList[i].reversed())
                print(xr[curX]!![curQI][k] + ' ')
            }
            print("\n")
        }
        print("\n\n")
    }
    // S
    for (k in 3 downTo 0) {
        print("Map for S${3-k}\n")
        for (i in cList.indices) { // h
            for (j in cList.indices) { // v
                val curX = cList[j][2].toString() + cList[j][1]
                val curQI = qToI(cList[j][0].toString() + cList[i].reversed())
                print(xs[curX]!![curQI][k] + ' ')
            }
            print("\n")
        }
        print("\n\n")
    }
    // T
    for (k in 3 downTo 0) {
        print("Map for T${3-k}\n")
        for (i in cList.indices) { // h
            for (j in cList.indices) { // v
                val curX = cList[j][2].toString() + cList[j][1]
                val curQI = qToI(cList[j][0].toString() + cList[i].reversed())
                print(xt[curX]!![curQI][k] + ' ')
            }
            print("\n")
        }
        print("\n\n")
    }

}

fun main() {
    createTable()
    println("-------------------------------")
    createKM()
}