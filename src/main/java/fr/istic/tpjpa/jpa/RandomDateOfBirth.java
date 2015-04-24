package fr.istic.tpjpa.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RandomDateOfBirth {

	private Date randomDate;
	
    public RandomDateOfBirth() {

    	
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1900, 2015);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        try {
			randomDate = new SimpleDateFormat("dd-MM-yyyy").parse(gc.get(gc.DAY_OF_MONTH)+ "-" +gc.get(gc.MONTH)+ "-" +gc.get(gc.YEAR));
		} catch (ParseException e) {
			e.printStackTrace();
		}

    }
    
    public Date getRandomDate(){
    	return randomDate;
    }

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}