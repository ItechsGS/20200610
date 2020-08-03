package com.prolificinteractive.materialcalendarview;

public interface OnWeekChangedListener {

    /**
     * Called upon change of the selected day
     *
     * @param widget the view associated with this listener
     * @param date   the month picked, as the first day of the month
     */
    void onWeekChanged(MaterialCalendarView widget, CalendarDay date);
}

