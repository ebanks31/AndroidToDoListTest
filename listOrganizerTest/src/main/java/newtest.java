import junit.framework.Assert;
import junit.framework.TestCase;


import java.util.ArrayList;

import org.junit.Test;

import com.example.android.rssfeed.ListOrganizerActivity;


public class newtest extends ListOrganizerActivity {

	@Test
	public void testRemoveDuplicatesMethod() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		Assert.assertEquals(2, finallist.size());
		
	}
	
	@Test
	public void testRemoveDuplicatesMethodWithEmptyString() {

		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		list.add("");
		list.add("");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		Assert.assertEquals(3, finallist.size());
		
	}
	
	@Test
	public void testRemoveDuplicatesMethodWithEmptySpaces() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		list.add(" ");
		list.add(" ");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		Assert.assertEquals(3, finallist.size());
		
	}
	
	@Test
	public void testRemoveDuplicatesMethodWithUpperCaseEndString() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("HellO");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		Assert.assertEquals(2, finallist.size());
		
	}

}
