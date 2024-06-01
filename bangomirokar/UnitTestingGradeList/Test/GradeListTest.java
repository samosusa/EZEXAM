import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class   GradeListTest {

    private GradeList list;

    //  Will be called before each test method
    //  So that there are no elements in a list before each test....
    //  Your choice of test architecture
    @BeforeEach void setUp(){
        System.out.println("setUp");

        list = new GradeList(6);
    }


    //  First test for zero position, null grade inserted case... check SWE topic for reference
    //  Have to expect a exception and specify the type by specifying the class
    @Test void addIndexZeroNull(){
        System.out.println("addIndexZeroNull");

        //  Can even test the message of the exception when storing it...
        var Grade = assertThrows(IllegalArgumentException.class, () -> list.addGrade(0,null));
        assertEquals("Null grade",Grade.getMessage());
    }

    @Test void addIndexZeroIndexEmpty(){
        System.out.println("addIndexZeroIndexEmpty");

        assertDoesNotThrow(() -> list.addGrade(0,new Grade(12,"SDJ")));
        assertEquals("{{SDJ: 12}}", list.toString());
    }

    @Test void addIndexZeroIndexNotEmpty(){
        System.out.println("addIndexZeroIndexNotEmpty");

        //  Need to add elements, before testing this case
        list.addGrade(0, new Grade(12, "SEP"));
        list.addGrade(1, new Grade(7, "SWE"));
        list.addGrade(2, new Grade(4, "DBZ"));

        assertDoesNotThrow(()->list.addGrade(0,new Grade(4,"DBZ")));
        assertEquals(4, list.size());
        assertEquals("{{DBZ: 4}, {SEP: 12}, {SWE: 7}, {DBZ: 4}}", list.toString());

    }
    @Test
    void addGradeAtFirstPosition() {
        Grade grade = new Grade(12, "Math");
        list.addGrade(0, grade);
        assertEquals(1, list.size());
        assertEquals(grade, list.getGrade(0));
    }
    @Test
    void addGradeAtLastPosition() {
        list.addGrade(0, new Grade(12, "Math"));
        list.addGrade(1, new Grade(10, "Science"));
        Grade grade = new Grade(7, "History");
        list.addGrade(2, grade);  // adding at last valid position
        assertEquals(3, list.size());
        assertEquals(grade, list.getGrade(2));
    }
    void addGradeBeyondMaxCapacity() {
        list.addGrade(0, new Grade(12, "Math"));
        list.addGrade(1, new Grade(10, "Science"));
        list.addGrade(2, new Grade(7, "History"));

        // Attempting to add a grade beyond capacity
        Grade extraGrade = new Grade(4, "Art");
        list.addGrade(3, extraGrade); // this should not be added

        assertEquals(3, list.size()); // size should still be 3
        assertNotEquals(extraGrade, list.getGrade(2)); // last grade should still be "History"
    }
}
    /*

1. ArrayList()
Z - check the ArrayList to be empty
O - not relevant
M - not relevant
B - not relevant
E - none

2. add(int index, T element)
Z1 - add element at index 0 in empty ArrayList
Z2 - add element at index 0 in ArrayList with 3 elements
O1 - add element at index 1 in empty ArrayList - expect exception
O2 - add element at index 1 in ArrayList with 1 element
M - add 3 elements in ArrayList with 2 elements
B - add element at index -1 - expect exception
E - tested in One and Bounds

3. add(T element)
Z - add element in empty ArrayList
O - add element in an ArrayList with 1 element
M - add 3 elements in ArrayList with 2 elements
B - tested in Zero
E - none

4. contains(T element)
Z - element searched in empty ArrayList - expect false
O - element searched in ArrayList with 1 element
M1 - element "B" searched in ArrayList with "A", "B" and "C" elements, - expect true
M2 - element "B" searched in ArrayList with "A", "C", "D" elements - expect false
B - tested in Zero
E - none

5. get(int index)
Z1 - get element at index 0 in empty ArrayList - expect exception
Z2 - get element at index 0 in ArrayList with 3 elements
O - get element at index 1 in ArrayList with 3 elements
M - get element at index 3 in ArrayList with 5 elements
B1 - get element at index -1 - expect exception
B2 - get element at index 3 in ArrayList with 2 elements
B3 - get element at index 3 in ArrayList with 1 element - expect exception
E - tested in Zero and Bounds

6. indexOf(T element)
Z - index of element in empty ArrayList - expect -1
O - index of element in ArrayList with 1 element - expect found
M1 - index of element in ArrayList with 3 elements - expect found
M2 - index of element in ArrayList with 3 elements - expect not found
B - tested in Zero
E - none

7. isEmpty()
Z - check for empty ArrayList - expect true
O - check for ArrayList with 1 element - expect false
M - check for ArrayList with 3 element - expect false
B - tested in Zero
E - none

8. isFull()
Z - check for an empty ArrayList - expect false
O - check for an ArrayList with 1 element - expect false
M - check for an ArrayList with 16 elements - expect false
B - check for an ArrayList with 17 elements - expect false
E - none

9. remove(int index)
Z1 - remove element at index 0 in an empty ArrayList - expect exception
Z2 - remove element at index 0 in an ArrayList with 3 elements
O - remove element at index 1 in an ArrayList with 3 elements
M - remove elements at index 1, 2 and 3 in ArrayList with 5 elements
B1 - remove element at index -1 - expect exception
B2 - remove element at index 2 in ArrayList with 3 elements
B3 - remove element at index 2 in ArrayList with 2 elements - expect exception
E - tested in Zero and Bounds

10. remove(T element)
Z - remove element in an empty ArrayList - expect exception
O - remove element in an ArrayList with 1 element
M - remove 2 elements in an ArrayList with 3 elements
B - remove element that in not in the ArrayList - expect exception
E - tested in Zero and Bounds

11. set(int index, T element)
Z1 - set element at index 0 in empty ArrayList - expect exception
Z2 - set element at index 0 in ArrayList with 3 elements
O - set element at index 1
M - set 3 elements in ArrayList with 4 elements
B1 - set element at index -1 - expect exception
B2 - set element at index 2 in ArrayList with 3 elements
B3 - set element at index 2 in ArrayList with 2 elements - expect exception
E - tested in Zero and Bounds

12. size()
Z - check size for empty ArrayList - expect 0
O - check size for ArrayList with 1 element - expect 1
M - check size for ArrayList with 3 elements - expect 3
B1 - check size for ArrayList with 16 elements - expect 16
B2 - check size for ArrayList with 17 elements - expect 17
E - none

13. toString()
Z - check for empty ArrayList - expect "{}"
O - check for ArrayList with 1 element - expect "{element}"
M - check for ArrayList with 3 elements - expect "{element1, element2, element3}"
B - tested in Zero
E - none

*/

  /*
  14.
 public T remove(int index)
 {
   //Test case: index<0
   //Test case: index>size-1
   //Test case: index>=0 and index<=size-1
   if (index < 0 || index > size -1)
   {
     throw new IndexOutOfBoundsException("index:" + index);
   }
   T result = list[index];

   //Test case: index=0 and size=1
   //Test case: index=0 and size>1
   //Test case: index=1 and size>index
   for (int i = index; i < size -1; i++)
   {
     list[i] = list[i + 1];
   }
   size--;
   return result;
 }
*/
