import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import com.example.android.rssfeed.ListOrganizerActivity;


public class CopyOfRssFeedActivityTest extends ActivityInstrumentationTestCase2<ListOrganizerActivity> {
	
	public CopyOfRssFeedActivityTest(Class activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public CopyOfRssFeedActivityTest() {
		super("com.my", ListOrganizerActivity.class);
		}

	@Override
	protected void setUp() throws Exception {
	super.setUp();

	//this.activity = this.getActivity();
	//this.solo = new Solo(getInstrumentation(), this.activity);
	}
	
	
	public void testRemoveDuplicatesMethod() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		assertEquals(2, finallist.size());
		
	}
	
	
	public void testRemoveDuplicatesMethodWithEmptyString() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		list.add("");
		list.add("");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		assertEquals(3, finallist.size());
		
	}
	
	
	public void testRemoveDuplicatesMethodWithEmptySpaces() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		list.add(" ");
		list.add(" ");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		assertEquals(3, finallist.size());
		
	}
	
	
	public void testRemoveDuplicatesMethodWithUpperCaseEndString() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("HellO");
		ArrayList<String> finallist =rs.removeDuplicates(list);
		
		assertEquals(2, finallist.size());
		
	}
	
	
	public void updateArrayWithValidItemFirstValue() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		String[] stringarray = {"Hello","Great","Time","Robot"};
		String item="Hello";
		String newitem="Hi";
		String[] finalstringarray =rs.updateArray(stringarray,item,newitem);
		
		assertEquals("Hi", finalstringarray[0]);
		
	}
	
	
	
	public void updateArrayWithValidItemLastItem() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		String[] stringarray = {"Hello","Great","Time","Robot"};
		String item="Robot";
		String newitem="Hi";
		String[] finalstringarray =rs.updateArray(stringarray,item,newitem);
		
		assertEquals("Hi", finalstringarray[3]);
		
	}
	
	
	public void updateArrayWithValidItemLastItemEmptyStringFirstItem() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		String[] stringarray = {"","Great","Time","Robot"};
		String item="Robot";
		String newitem="";
		String[] finalstringarray =rs.updateArray(stringarray,item,newitem);
		
		assertEquals("Robot", finalstringarray[3]);
		
	}
	
	
	public void updateArrayWithValidItemLastItemEmptyStringLastItem() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		String[] stringarray = {"Hello","Great","Time","Robot"};
		String item="Robot";
		String newitem="";
		String[] finalstringarray =rs.updateArray(stringarray,item,newitem);
		
		assertEquals("Robot", finalstringarray[3]);
		
	}

	
	public void updateArrayWithValidItemLastItemEmptyStringFirstAndLastItem() {
		fail("Not yet implemented");
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		String[] stringarray = {"Robot","Great","Time","Robot"};
		String item="Robot";
		String newitem="";
		String[] finalstringarray =rs.updateArray(stringarray,item,newitem);
		
		assertEquals("", finalstringarray[3]);
		assertEquals("", finalstringarray[0]);
		
	}
	
	
	public void ConvertArrayListtoArrayWithValidArrayList() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Hello");
		list.add("hello");
		list.add(" ");
		list.add(" ");
		String[] finalarraylist =rs.ConvertArrayListtoArray(list);
		
		assertEquals(5, finalarraylist.length);
		
	}
	
	
	public void ConvertArrayListtoArrayWithValidArrayListOneValue() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		String[] finalarraylist =rs.ConvertArrayListtoArray(list);
		
		assertEquals(1, finalarraylist.length);
		
	}
	
	
	public void ConvertArrayListtoArrayWithValidArrayListNoValue() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		String[] finalarraylist =rs.ConvertArrayListtoArray(list);
		
		assertEquals(0, finalarraylist.length);
		
	}
	
	
	
	
	public void deleteItemFromArrayWithValidValuesFoundInArrayList() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("hello");
		list.add(" ");

		String[] finalarraylist =rs.deleteItemFromArray(list,"Hello");
		
		assertEquals("Hello", finalarraylist[0]);
		
	}
	
	
	public void deleteItemFromArrayWithValidValuesNotFoundInArrayList() {
		ListOrganizerActivity rs = new ListOrganizerActivity();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("hello");
		list.add(" ");

		String[] finalarraylist =rs.deleteItemFromArray(list,"Notfound");
		
		assertEquals("Hello", finalarraylist[0]);
		
	}
}

