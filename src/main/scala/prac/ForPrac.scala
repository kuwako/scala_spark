package prac

object ForPrac {
    def main(args: Array[String]) :Unit = {
        var buff = ""
        val sample = "sample"

        sample.foreach(node => {
            println(node)
            buff = buff + node
        })

        println("buff is ")
        println(buff)
    }
}
