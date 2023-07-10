package rs.raf.vezbe11.data.models

import rs.raf.vezbe11.presentation.view.recycler.MealCardItem

class MyDay(private var day: String) {
    private var breakfastList: MutableList<MealCardItem> = mutableListOf()
    private var lunchList: MutableList<MealCardItem> = mutableListOf()
    private var dinnerList: MutableList<MealCardItem> = mutableListOf()
    private var snackList: MutableList<MealCardItem> = mutableListOf()

    fun addBreakfastItem(item: MealCardItem) {
        breakfastList.add(item)
    }

    fun addLunchItem(item: MealCardItem) {
        lunchList.add(item)
    }

    fun addDinnerItem(item: MealCardItem) {
        dinnerList.add(item)
    }

    fun addSnackItem(item: MealCardItem) {
        snackList.add(item)
    }

    fun getDay(): String {
        return day
    }

    fun setDay(value: String) {
        day = value
    }

    fun getBreakfastList(): MutableList<MealCardItem> {
        return breakfastList
    }

    fun setBreakfastList(list: MutableList<MealCardItem>) {
        breakfastList.clear()
        breakfastList = list
    }

    fun getLunchList(): MutableList<MealCardItem> {
        return lunchList
    }

    fun setLunchList(list: MutableList<MealCardItem>) {
        lunchList.clear()
        lunchList.addAll(list)
    }

    fun getDinnerList(): MutableList<MealCardItem> {
        return dinnerList
    }

    fun setDinnerList(list: MutableList<MealCardItem>) {
        dinnerList.clear()
        dinnerList.addAll(list)
    }

    fun getSnackList(): MutableList<MealCardItem> {
        return snackList
    }

    fun setSnackList(list: MutableList<MealCardItem>) {
        snackList.clear()
        snackList.addAll(list)
    }
}
