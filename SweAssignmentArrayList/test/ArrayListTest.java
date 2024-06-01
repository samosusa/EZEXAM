import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.collection.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest<T>
{
    private ArrayList<String> arrayList;
    @BeforeEach void setUp()
    {
        arrayList=new ArrayList<>();
    }
  /*
  1. ArrayList()
  Z - check the ArrayList to be empty
  O - not relevant
  M - not relevant
  B - not relevant
  E - none
 */

    //The ArrayList is empty - passed
    @Test void ArrayList_Zero_ZeroElements()
    {
        ArrayList<T> test = assertDoesNotThrow(()-> new ArrayList<>());
        assertEquals("{}", test.toString());
    }

  /*
  2. add(int index, T element)
  Z1 - add element at index 0 in empty ArrayList
  Z2 - add element at index 0 in ArrayList with 3 elements
  O1 - add element at index 1 in empty ArrayList - expect exception
  O2 - add element at index 1 in ArrayList with 1 element
  M - add 3 elements in ArrayList with 2 elements
  B - add element at index -1 - expect exception
  E - tested in One and Bounds
 */

    //The ArrayList contains the new element - Passed
    @Test void add_Zero1_IndexZero_EmptyArrayList()
    {
        assertDoesNotThrow(()-> arrayList.add(0, "Element"));
        assertEquals("{Element}", arrayList.toString());
    }

    //The ArrayList contains the new element - Passed
    @Test void add_Zero2_IndexZero_ThreeElements()
    {
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        assertDoesNotThrow(()-> arrayList.add(0, "d"));
        assertEquals("{d, a, b, c}", arrayList.toString());
    }

    //Passed
    @Test void add_One1_IndexOne_EmptyArrayList()
    {
        assertThrows(IndexOutOfBoundsException.class, ()-> arrayList.add(1, "Element"));
    }

    //The ArrayList contains the new element - Passed
    @Test void add_One2_IndexOne_OneElement()
    {
        arrayList.add("a");
        assertDoesNotThrow(()-> arrayList.add(0, "b"));
        assertEquals("{b, a}", arrayList.toString());
    }

    //The ArrayList contains the new elements at the specified indexes - passed
    @Test void add_Many_ThreeElementsInTwoElementsArrayList()
    {
        //a d e b c
        arrayList.add("a");
        arrayList.add("b");
        assertDoesNotThrow(()-> arrayList.add(2, "c"));
        assertDoesNotThrow(()-> arrayList.add(1, "d"));
        assertDoesNotThrow(()-> arrayList.add(2, "e"));
        assertEquals("{a, d, e, b, c}", arrayList.toString());
    }

    //Passed
    @Test void add_Bounds_NegativeIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()-> arrayList.add(-1, "Element"));
    }

  /*
  3. add(T element)
  Z - add element in empty ArrayList
  O - add element in an ArrayList with 1 element
  M - add 3 elements in ArrayList with 2 elements
  B - tested in Zero
  E - none
   */

    //The ArrayList contains the new element - Passed
    @Test void add_Zero_EmptyArrayList()
    {
        assertDoesNotThrow(()-> arrayList.add("Element"));
        assertEquals("{Element}", arrayList.toString());
    }

    //The ArrayList contains the new element - Passed
    @Test void add_One_OneElementArrayList()
    {
        arrayList.add("a");
        assertDoesNotThrow(()-> arrayList.add("b"));
        assertEquals("{a, b}", arrayList.toString());
    }

    //The ArrayList contains the new elements - Passed
    @Test void add_Many_ThreeElements_TwoElementsArrayList()
    {
        arrayList.add("a");
        arrayList.add("b");
        assertDoesNotThrow(()-> arrayList.add("c"));
        assertDoesNotThrow(()-> arrayList.add("d"));
        assertDoesNotThrow(()-> arrayList.add("e"));
        assertEquals("{a, b, c, d, e}", arrayList.toString());
    }

  /*
  4. contains(T element)
  Z - element searched in empty ArrayList - expect false
  O - element searched in ArrayList with 1 element
  M1 - element "B" searched in ArrayList with "A", "B" and "C" elements, - expect true
  M2 - element "B" searched in ArrayList with "A", "C", "D" elements - expect false
  B - tested in Zero
  E - none
   */

    //Passed
    @Test void contains_Zero_EmptyArrayList()
    {
        boolean result=assertDoesNotThrow(()-> arrayList.contains("Element"));
        assertFalse(result);
    }

    //Passed
    @Test void contains_One_OneElementArrayList()
    {
        arrayList.add("Element");
        boolean result=assertDoesNotThrow(()-> arrayList.contains("Element"));
        assertTrue(result);
    }

    //Passed
    @Test void contains_Many1_ThreeElementsArrayList_Found()
    {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        boolean result=assertDoesNotThrow(()-> arrayList.contains("B"));
        assertTrue(result);
    }

    //Passed
    @Test void contains_Many2_ThreeElementsArrayList_NotFound()
    {
        arrayList.add("A");
        arrayList.add("C");
        arrayList.add("D");
        boolean result=assertDoesNotThrow(()-> arrayList.contains("B"));
        assertFalse(result);
    }

  /*
  7. isEmpty()
  Z - check for empty ArrayList - expect true
  O - check for ArrayList with 1 element - expect false
  M - check for ArrayList with 3 elements - expect false
  B - tested in Zero
  E - none
   */

    //Passed
    @Test void isEmpty_Zero_EmptyArrayList()
    {
        boolean result=assertDoesNotThrow(()-> arrayList.isEmpty());
        assertTrue(result);
    }

    //Passed
    @Test void isEmpty_One_OneElementArrayList()
    {
        arrayList.add("Element");
        boolean result=assertDoesNotThrow(()-> arrayList.isEmpty());
        assertFalse(result);
    }

    //Passed
    @Test void isEmpty_Many_ThreeElementsArrayList()
    {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("D");
        boolean result=assertDoesNotThrow(()-> arrayList.isEmpty());
        assertFalse(result);
    }

 /*
  8. isFull()
  Z - check for an empty ArrayList - expect false
  O - check for an ArrayList with 1 element - expect false
  M - check for an ArrayList with 16 elements - expect false
  B - not possible because array list implementation is unbounded -> no limit on max elements
  E - none
   */

    // Z
    // Not passed due to isFull() method not implemented correctly
    // Expected -> false
    // Actual -> true
    @Test void isFull_empty_arraylist_returns_false() {
        assertEquals(false, arrayList.isFull());
    }

    // O
    // Not passed due to isFull() method not implemented correctly
    // Expected -> false
    @Test void isFull_arraylist_with_one_element_returns_false() {
        // Arrange
        arrayList.add("test");

        // Act
        boolean result = arrayList.isFull();

        // Assert
        assertEquals(false, result);
    }

    // M
    // Not passed due to isFull() method not implemented correctly
    // Expected -> false
    @Test void isFull_arraylist_with_many_elements_returns_false() {
        // Arrange
        for (int i = 0; i <= 15; i++) {
            arrayList.add("Test " + i);
        }

        // Act
        boolean result = arrayList.isFull();

        // Assert
        assertEquals(false, result);
    }

  /*
  9. remove(int index)
Z1 - remove element at index 0 in an empty ArrayList - expect exception
O - remove element at index 1 in an ArrayList with 3 elements
M - remove 3 elements with 5 elements
B1 - remove element at index -1 - expect exception
B2 - remove element at index 2 in ArrayList with 3 elements - expect pass
B3 - remove element at index 2 in ArrayList with 2 elements - expect exception
E - tested in Zero and Bounds
   */

    //Z1
    // Passed
    @Test void remove_index_0_when_arraylist_empty_throws_exception() {
        // Act + Assert because exception
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(0));
    }

    // O
    // Passed
    @Test void remove_index_0_when_arraylist_contains_3_elements_expect_2_elements_remaining() {
        // Arrange
        arrayList.add("test1");
        arrayList.add("test2");
        arrayList.add("test3");

        // Act
        arrayList.remove(1);

        // Assert
        assertEquals(2, arrayList.size());
    }

    // M
    // Passed
    @Test void remove_3_elements_when_arraylist_contains_5_elements_expect_2_elements_remaining() {
        // Arrange
        for (int i = 0; i <= 4; i++) {
            arrayList.add("Test " + i);
        }

        // Act
        arrayList.remove(0);
        arrayList.remove(0);
        arrayList.remove(0);

        // Assert
        assertEquals(2, arrayList.size());
    }

    // B1
    //Passed
    @Test void remove_element_index_minus_1_expect_outofbounds_exception() {
        // Act + Assert
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(-1));
    }

    // B2
    //Passed
    @Test void remove_element_index_2_arraylist_contains_3_elements_passes() {
        // Arrange
        for (int i = 0; i <= 2; i++) {
            arrayList.add("Test " + i);
        }

        // Act + Assert
        assertDoesNotThrow(() -> arrayList.remove(2));
    }

    // B3
    // Passed
    @Test void remove_element_index_3_arraylist_contains_3_elements_throws_outofbounds_exception() {
        // Arrange
        for (int i = 0; i <= 2; i++) {
            arrayList.add("Test " + i);
        }

        // Act + Assert
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(3));
    }
    /*
    10. remove(T element)

    Z - remove element in an empty ArrayList - expect exception
    O - remove element in an ArrayList with 1 element
    M - remove 2 elements in an ArrayList with 3 elements
    B - remove element that in not in the ArrayList - expect exception
    E    - tested in Zero and Bounds
     */

    //  Passed
    @Test void remove_From_Empty_ArrayList(){
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(0));
    }

    //  Passed
    @Test void remove_From_Not_Empty_ArrayList(){
        arrayList.add("A");
        String result = assertDoesNotThrow(() -> arrayList.remove("A"));
        assertEquals("A",result);
    }

    //  Passed
    @Test void remove_Many_From_Not_Empty_ArrayList(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        String result1 = assertDoesNotThrow(() -> arrayList.remove("A"));
        String result2 = assertDoesNotThrow(() -> arrayList.remove("B"));

        assertEquals("A",result1);
        assertEquals("B",result2);


        assertEquals(1, arrayList.size());
        assertFalse(arrayList.contains("A"));
        assertFalse(arrayList.contains("B"));
    }

    //  Passed
    @Test void remove_Element_Not_Contained_In_ArrayList(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertThrows(IllegalStateException.class, () -> arrayList.remove("D"));
    }

        /*
    11. set(int index, T element)
    Z1 - set element at index 0 in empty ArrayList - expect exception
    Z2 - set element at index 0 in ArrayList with 3 elements
    O - set element at index 1
    M - set 3 elements in ArrayList with 4 elements
    B1 - set element at index -1 - expect exception
    B2 - set element at index 2 in ArrayList with 3 elements
    B3 - set element at index 2 in ArrayList with 2 elements - expect exception
    E - tested in Zero and Bounds
     */

    //  Passed
    @Test void set_Element_In_EmptyArray(){

        //Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(0, "A"));
        //assertEquals("Index 0 out of bounds for length 0", exception.getMessage());
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(0, "A"));
    }


    //  Passed
    @Test void set_Element_In_Not_EmptyArray(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertDoesNotThrow(() -> arrayList.set(0,"D"));
        assertEquals(3, arrayList.size());
        assertEquals("{D, B, C}", arrayList.toString());

    }

    //  Passed
    @Test void set_Element_At_Index_One(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertDoesNotThrow(() -> arrayList.set(1,"D"));
        assertEquals(3, arrayList.size());
        assertEquals("{A, D, C}", arrayList.toString());
    }

    //  Passed
    @Test void set_Three_Elements(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertDoesNotThrow(() -> arrayList.set(0,"X"));
        assertDoesNotThrow(() -> arrayList.set(2,"Y"));
        assertDoesNotThrow(() -> arrayList.set(3,"Z"));
        assertEquals(4, arrayList.size());
        assertEquals("{X, B, Y, Z}", arrayList.toString());
    }
    //  Passed
    @Test void set_At_Negative_Index(){
        //Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-1, "A"));
        //assertEquals("Index -1 out of bounds for length 0", exception.getMessage());
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-1, "A"));
    }

    //  Passed
    @Test void set_Last_Element(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertDoesNotThrow(() -> arrayList.set(2,"D"));
        assertEquals(3, arrayList.size());
        assertEquals("{A, B, D}", arrayList.toString());
    }
    //  Passed
    @Test void set_Last_Out_Of_Bounds(){
        arrayList.add("A");
        arrayList.add("B");

        //Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(3, "D"));
        //assertEquals("Index -1 out of bounds for length 0", exception.getMessage());
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(3, "D"));
    }

  /*
  13. toString()
  Z - check for empty ArrayList - expect "{}"
  O - check for ArrayList with 1 element - expect "{element}"
  M - check for ArrayList with 3 elements - expect "{element1, element2, element3}"
  B - tested in Zero
  E - none
   */

    //Passed
    @Test void toString_Zero_EmptyArrayList()
    {
        String result=assertDoesNotThrow(()-> arrayList.toString());
        assertEquals("{}", result);
    }

    //Passed
    @Test void toString_One_OneElementArrayList()
    {
        arrayList.add("Element");
        String result=assertDoesNotThrow(()-> arrayList.toString());
        assertEquals("{Element}", result);
    }

    //Passed
    @Test void toString_Many_ThreeElementsArrayList()
    {
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        String result=assertDoesNotThrow(()-> arrayList.toString());
        assertEquals("{a, b, c}", result);
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
M - check for ArrayList with 3 elements - expect false
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

}