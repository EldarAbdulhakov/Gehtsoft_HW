package hw01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomListTest {

    static Stream<Arguments> integerLists() {
        CustomList<Integer> integerCustomList = new CustomList<>();
        integerCustomList.add(1);
        integerCustomList.add(53);
        integerCustomList.add(24);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(53);
        integerList.add(24);

        return Stream.of(
                Arguments.of(integerCustomList),
                Arguments.of(integerList)
        );
    }

    static Stream<Arguments> stringLists() {
        CustomList<String> stringCustomList = new CustomList<>();
        stringCustomList.add("1");
        stringCustomList.add("53");
        stringCustomList.add("24");

        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("53");
        stringList.add("24");

        return Stream.of(
                Arguments.of(stringCustomList),
                Arguments.of(stringList)
        );
    }

    @ParameterizedTest
    @MethodSource({"stringLists"})
    public void testAddInEmptyStringList(List<String> stringLists) {
        stringLists.clear();

        stringLists.add("51");

        Assertions.assertEquals("51", stringLists.get(0));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testAddInEmptyList(List<Integer> integerList) {
        integerList.clear();

        integerList.add(51);

        Assertions.assertEquals(51, integerList.get(0));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testAddNull(List<Integer> integerList) {
        integerList.add(null);

        Assertions.assertNull(integerList.get(3));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testAddInList(List<Integer> integerList) {
        integerList.add(100);

        Assertions.assertEquals(100, integerList.get(3));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testGet(List<Integer> integerList) {
        Assertions.assertEquals(53, integerList.get(1));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testGetEmptyList(List<Integer> integerList) {
        integerList.clear();

        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.get(0));

        Assertions.assertEquals("Index 0 out of bounds for length 0", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testGetNegativeIndex(List<Integer> integerList) {
        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.get(-1));

        Assertions.assertEquals("Index -1 out of bounds for length 3", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testGetOverSizeIndex(List<Integer> integerList) {
        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.get(4));

        Assertions.assertEquals("Index 4 out of bounds for length 3", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testSet(List<Integer> integerList) {
        integerList.set(1, 14);

        Assertions.assertEquals(14, integerList.get(1));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testSetNegativeIndex(List<Integer> integerList) {
        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.set(-1, 14));

        Assertions.assertEquals("Index -1 out of bounds for length 3", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testSetOverSizeIndex(List<Integer> integerList) {
        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.set(3, 14));

        Assertions.assertEquals("Index 3 out of bounds for length 3", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testSetNull(List<Integer> integerList) {
        integerList.set(0, null);

        Assertions.assertNull(integerList.get(0));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testRemove(List<Integer> integerList) {
        integerList.remove(1);

        Assertions.assertEquals(2, integerList.size());
        Assertions.assertEquals(1, integerList.get(0));
        Assertions.assertEquals(24, integerList.get(1));
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testRemoveToEmptyList(List<Integer> integerList) {
        integerList.clear();

        Assertions.assertEquals(0, integerList.size());
        Assertions.assertTrue(integerList.isEmpty());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testRemoveOverSizeIndex(List<Integer> integerList) {
        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.remove(3));

        Assertions.assertEquals("Index 3 out of bounds for length 3", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testRemoveNegativeIndex(List<Integer> integerList) {
        IndexOutOfBoundsException exception = Assertions.assertThrowsExactly(
                IndexOutOfBoundsException.class,
                () -> integerList.remove(-1));

        Assertions.assertEquals("Index -1 out of bounds for length 3", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testClear(List<Integer> integerList) {
        integerList.clear();

        Assertions.assertTrue(integerList.isEmpty());
        Assertions.assertEquals(0, integerList.size());
    }

    @ParameterizedTest
    @MethodSource({"integerLists"})
    public void testEmptyList(List<Integer> integerList) {
        integerList.clear();

        Assertions.assertEquals(0, integerList.size());
    }
}
