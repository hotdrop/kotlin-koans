package iii_conventions

class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int =
            if(this.toInt() > other.toInt()) 1 else -1

    override fun equals(other: Any?): Boolean = sameDate(other as MyDate) || super.equals(other)

    private fun sameDate(d: MyDate): Boolean {
        return (year == d.year) && (month == d.month) && (dayOfMonth == d.dayOfMonth)
    }

    private fun toInt(): Int = (year * 10000) + (month * 100) + dayOfMonth
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(rti: RepeatedTimeInterval): MyDate = addTimeIntervals(rti.timeInterval, rti.number)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
    operator fun contains(d: MyDate): Boolean = (start <= d && d <= endInclusive)
}

class DateIterator(range: DateRange): Iterator<MyDate> {
    var currentDate = range.start
    val endDate = range.endInclusive

    override fun next(): MyDate {
        val result = currentDate
        currentDate = currentDate.nextDay()
        return result
    }

    override fun hasNext(): Boolean = (currentDate <= endDate)
}
