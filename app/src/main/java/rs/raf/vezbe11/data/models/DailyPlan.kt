package rs.raf.vezbe11.data.models

class DailyPlan {
    val monday = MyDay("Monday")
    val tuesday = MyDay("Tuesday")
    val wednesday = MyDay("Wednesday")
    val thursday = MyDay("Thursday")
    val friday = MyDay("Friday")
    val saturday = MyDay("Saturday")
    val sunday = MyDay("Sunday")

    fun getMondayDP(): MyDay {
        return monday
    }

    fun getTuesdayDP(): MyDay {
        return tuesday
    }

    fun getWednesdayDP(): MyDay {
        return wednesday
    }

    fun getThursdayDP(): MyDay {
        return thursday
    }

    fun getFridayDP(): MyDay {
        return friday
    }

    fun getSaturdayDP(): MyDay {
        return saturday
    }

    fun getSundayDP(): MyDay {
        return sunday
    }

    fun formatEmailBody() : String{
        var body = ""

        body += "Dear user, \n Here is your weekly daily eating plan:\n"

        //MONDAY
        body += "Monday:\n"

        body += "   Breakfast:\n"
        for(i in monday.getBreakfastList().indices)
        {
            body += "       " + monday.getBreakfastList()[i].strMeal
            if(i != monday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in monday.getLunchList().indices)
        {
            body += "       " + monday.getLunchList()[i].strMeal
            if(i != monday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in monday.getDinnerList().indices)
        {
            body += "       " + monday.getDinnerList()[i].strMeal
            if(i != monday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in monday.getSnackList().indices)
        {
            body += "       " + monday.getSnackList()[i].strMeal
            if(i != monday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }

        //TUESDAY
        body += "Tuesday:\n"

        body += "   Breakfast:\n"
        for(i in tuesday.getBreakfastList().indices)
        {
            body += "       " + tuesday.getBreakfastList()[i].strMeal
            if(i != tuesday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in tuesday.getLunchList().indices)
        {
            body += "       " + tuesday.getLunchList()[i].strMeal
            if(i != tuesday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in tuesday.getDinnerList().indices)
        {
            body += "       " + tuesday.getDinnerList()[i].strMeal
            if(i != tuesday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in tuesday.getSnackList().indices)
        {
            body += "       " + tuesday.getSnackList()[i].strMeal
            if(i != tuesday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }


        //WEDNESDAY
        body += "Wednesday:\n"

        body += "   Breakfast:\n"
        for(i in wednesday.getBreakfastList().indices)
        {
            body += "       " + wednesday.getBreakfastList()[i].strMeal
            if(i != wednesday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in wednesday.getLunchList().indices)
        {
            body += "       " + wednesday.getLunchList()[i].strMeal
            if(i != wednesday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in wednesday.getDinnerList().indices)
        {
            body += "       " + wednesday.getDinnerList()[i].strMeal
            if(i != wednesday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in wednesday.getSnackList().indices)
        {
            body += "       " + wednesday.getSnackList()[i].strMeal
            if(i != wednesday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }

        //thursday
        body += "Thursday:\n"

        body += "   Breakfast:\n"
        for(i in thursday.getBreakfastList().indices)
        {
            body += "       " + thursday.getBreakfastList()[i].strMeal
            if(i != thursday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in thursday.getLunchList().indices)
        {
            body += "       " + thursday.getLunchList()[i].strMeal
            if(i != thursday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in thursday.getDinnerList().indices)
        {
            body += "       " + thursday.getDinnerList()[i].strMeal
            if(i != thursday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in thursday.getSnackList().indices)
        {
            body += "       " + thursday.getSnackList()[i].strMeal
            if(i != thursday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }

        //friday
        body += "friday:\n"

        body += "   Breakfast:\n"
        for(i in friday.getBreakfastList().indices)
        {
            body += "       " + friday.getBreakfastList()[i].strMeal
            if(i != friday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in friday.getLunchList().indices)
        {
            body += "       " + friday.getLunchList()[i].strMeal
            if(i != friday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in friday.getDinnerList().indices)
        {
            body += "       " + friday.getDinnerList()[i].strMeal
            if(i != friday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in friday.getSnackList().indices)
        {
            body += "       " + friday.getSnackList()[i].strMeal
            if(i != friday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }

        //saturday
        body += "saturday:\n"

        body += "   Breakfast:\n"
        for(i in saturday.getBreakfastList().indices)
        {
            body += "       " + saturday.getBreakfastList()[i].strMeal
            if(i != saturday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in saturday.getLunchList().indices)
        {
            body += "       " + saturday.getLunchList()[i].strMeal
            if(i != saturday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in saturday.getDinnerList().indices)
        {
            body += "       " + saturday.getDinnerList()[i].strMeal
            if(i != saturday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in saturday.getSnackList().indices)
        {
            body += "       " + saturday.getSnackList()[i].strMeal
            if(i != saturday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }

        //sunday
        body += "sunday:\n"

        body += "   Breakfast:\n"
        for(i in sunday.getBreakfastList().indices)
        {
            body += "       " + sunday.getBreakfastList()[i].strMeal
            if(i != sunday.getBreakfastList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Lunch:\n"
        for(i in sunday.getLunchList().indices)
        {
            body += "       " + sunday.getLunchList()[i].strMeal
            if(i != sunday.getLunchList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Dinner:\n"
        for(i in sunday.getDinnerList().indices)
        {
            body += "       " + sunday.getDinnerList()[i].strMeal
            if(i != sunday.getDinnerList().size-1) body += ",\n"
            else body += "\n"
        }

        body += "   Snack:\n"
        for(i in sunday.getSnackList().indices)
        {
            body += "       " + sunday.getSnackList()[i].strMeal
            if(i != sunday.getSnackList().size-1) body += ",\n"
            else body += "\n"
        }

        return body
    }

}