import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class QuickSelectPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_swap = "swap";
	protected static final String EX_NAME_choosePivot = "choosePivot";
	protected static final String EX_NAME_partition = "partition";
	protected static final String EX_NAME_quickSelect = "quickSelect";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines_swap__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines_choosePivot__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines_partition__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	@Test(timeout = 666)
	public void pubTest__Intestines_quickSelect__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__swap() {
		for (int pass = 0; pass < 10; pass++) {
			int l = 2 + RND.nextInt(666), i = RND.nextInt(l), j = RND.nextInt(l);
			Foo[] in = new Foo[l], expected = new Foo[l], out;
			Foo atI = new Foo(RND.nextInt()), atJ = new Foo(RND.nextInt());
			for (int x = 0; x < l; x++) {
				in[x] = expected[x] = new Foo(RND.nextInt());
			}
			in[i] = expected[j] = atI;
			in[j] = expected[i] = atJ;
			out = new QuickSelect<Foo>().swap(in, i, j);
			assertSame(in, out);
			for (int x = 0; x < l; x++) {
				assertSame(expected[x], out[x]);
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__choosePivot() {
		Integer[] inEx = new Integer[]{2, 5, 1, -5, 3};
		int pivotEx = new QuickSelect<Integer>().choosePivot(inEx, 0, inEx.length - 1);
		assertEquals("Pivot index of " + Arrays.toString(inEx), 0, pivotEx);
		// -----
		Integer[] in = new Integer[20];
		java.util.Random rand = new java.util.Random(23);
		for (int i = 0; i < in.length; i++) {
			in[i] = rand.nextInt(1000);
		}
		int pivot = new QuickSelect<Integer>().choosePivot(in, 0, in.length - 1);
		assertEquals("Pivot index of " + Arrays.toString(in), 9, pivot);
	}

	@Test(timeout = 666)
	public void pubTest__partition() {
		Integer[] ex = new Integer[20];
		java.util.Random rand = new java.util.Random(23);
		for (int i = 0; i < ex.length; i++) {
			ex[i] = rand.nextInt(1000);
		}
		Integer[] ex1 = java.util.Arrays.copyOf(ex, ex.length);
		int pivot = new QuickSelect<Integer>().choosePivot(ex1, 0, ex1.length - 1);
		Integer pivotVal = ex1[pivot];
		pivot = new QuickSelect<Integer>().partition(ex1, pivot, 0, ex1.length - 1);
		assertSame("returned pivot index does not point to chosen pivot value", pivotVal, ex1[pivot]);
		for (int i = 0; i < ex1.length; i++) {
			if (i < pivot) {
				assertTrue(ex1[i] + " expected left of pivot " + pivotVal, ex1[i] <= pivotVal);
			} else if (i > pivot) {
				assertTrue(ex1[i] + " expected right of pivot " + pivotVal, ex1[i] >= pivotVal);
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__quickSelect__largest() {
		Integer[] ex = new Integer[20];
		java.util.Random rand = new java.util.Random(23);
		for (int i = 0; i < ex.length; i++) {
			ex[i] = rand.nextInt(1000);
		}
		Integer[] sorted = Arrays.copyOf(ex, ex.length);
		Arrays.sort(sorted);
		Integer max = new QuickSelect<Integer>().quickSelect(ex, ex.length - 1);
		assertEquals("Largest element of random array" + Arrays.toString(ex), sorted[sorted.length - 1], max);
	}

	@Test(timeout = 666)
	public void pubTest__quickSelect__secondSmallest() {
		Integer[] ex = new Integer[20];
		java.util.Random rand = new java.util.Random(23);
		for (int i = 0; i < ex.length; i++) {
			ex[i] = rand.nextInt(1000);
		}
		Integer[] sorted = Arrays.copyOf(ex, ex.length);
		Arrays.sort(sorted);
		Integer secondSmallest = new QuickSelect<Integer>().quickSelect(ex, 1);
		assertEquals("Second smallest element of random array" + Arrays.toString(ex), sorted[1], secondSmallest);
	}

	private static final Foo[] DATA_FOO = new Foo[42_000 + RND.nextInt(4711)], DATA_FOO_SORTED = new Foo[DATA_FOO.length];

	static {
		for (int x = 0; x < DATA_FOO.length; x++) {
			DATA_FOO[x] = DATA_FOO_SORTED[x] = new Foo(RND.nextInt());
		}
		Arrays.sort(DATA_FOO_SORTED);
	}

	@Test(timeout = 6666)
	public void pubTest__quickSelect__random() {
		for (int pass = 0; pass < 20; pass++) {
			Foo[] dataFooClone = Arrays.copyOf(DATA_FOO, DATA_FOO.length);
			Foo nth = new QuickSelect<Foo>().quickSelect(dataFooClone, pass);
			assertSame(DATA_FOO_SORTED[pass], nth);
		}
		for (int pass = 0; pass < 20; pass++) {
			Foo[] dataFooClone = Arrays.copyOf(DATA_FOO, DATA_FOO.length);
			int n = RND.nextInt(DATA_FOO.length);
			Foo nth = new QuickSelect<Foo>().quickSelect(dataFooClone, n);
			assertSame(DATA_FOO_SORTED[n], nth);
		}
		for (int pass = 0; pass < 20; pass++) {
			Foo[] dataFooClone = Arrays.copyOf(DATA_FOO, DATA_FOO.length);
			Foo nth = new QuickSelect<Foo>().quickSelect(dataFooClone, DATA_FOO.length - 1 - pass);
			assertSame(DATA_FOO_SORTED[DATA_FOO.length - 1 - pass], nth);
		}
	}

	// ========== HELPER ==========
	private static class Foo implements Comparable<Foo> {
		private final int v;

		private Foo(int v) {
			this.v = v;
		}

		@Override
		public int compareTo(Foo foo) {
			return Integer.compare(v, foo.v);
		}
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION!
	private static Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	private static Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}

	private void check__Intestines() {
		@SuppressWarnings("rawtypes") Class<QuickSelect> clazz = QuickSelect.class;
		assertTrue(clazz + " must be public!", Modifier.isPublic(clazz.getModifiers()));
		assertFalse(clazz + " must not be abstract!", Modifier.isAbstract(clazz.getModifiers()));
		assertFalse(clazz + " must not be an annotation!", clazz.isAnnotation());
		assertFalse(clazz + " must not be an enumeration!", clazz.isEnum());
		assertFalse(clazz + " must not be an interface!", clazz.isInterface());
		assertSame(clazz + " must extend a certain super-class!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " must implement a certain number of interfaces!", 1, clazz.getInterfaces().length);
		assertSame(clazz + " must implement certain interfaces!", IQuickSelect.class, clazz.getInterfaces()[0]);
		assertEquals(clazz + " must declare a certain number of inner annotations!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " must declare a certain number of inner classes!", 0, getDeclaredClasses(clazz).length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " must declare a certain number of fields!", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals(clazz + " must declare a certain number of constructors (possibly including default constructor)!", 1, constructors.length);
		for (Constructor<?> constructor : constructors) {
			assertTrue(constructor + " - constructor must be public!", Modifier.isPublic(constructor.getModifiers()));
			assertEquals(constructor + " - constructor must have a certain number of parameters!", 0, constructor.getParameterTypes().length);
		}
		Method[] methods = getDeclaredMethods(clazz), interfaceMethods = getDeclaredMethods(IQuickSelect.class);
		assertTrue(clazz + " must declare a certain number of methods!", methods.length == interfaceMethods.length || methods.length == interfaceMethods.length + 1);
		int publicMethodCount = 0, privateMethodCount = 0;
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers())) {
				publicMethodCount++;
			} else if (Modifier.isPrivate(method.getModifiers())) {
				privateMethodCount++;
			}
			assertFalse(method + " - method must not be abstract!", Modifier.isAbstract(method.getModifiers()));
			assertFalse(method + " - method must not be static!", Modifier.isStatic(method.getModifiers()));
		}
		assertTrue("The methods required by the interface must be public - all other must be private!", publicMethodCount == interfaceMethods.length && methods.length == publicMethodCount + privateMethodCount);
	}
}