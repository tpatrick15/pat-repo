package com.weblogmaster.app;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator {

	  Map base;
	  public ValueComparator(Map base) {
	      this.base = base;
	  }

	  public int compare(Object a, Object b) {
		  try{
	    if((Integer)base.get(a) < (Integer)base.get(b)) {
	      return 1;
	    } else if((Integer)base.get(a) == (Integer)base.get(b)) {
	      return 0;
	    } else {
	      return -1;
	    }
		  }
		  catch(Exception e){
			  System.out.println("Error here:"+e.getMessage());
			  e.printStackTrace();
			  return -1;
		  }
	  }
	}
