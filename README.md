# Plain Text Comparator
Get equals & differences of strings vector on JAVA.


----------

**Use:**

    import com.tarikcurto.plain.compare.Comparator;
    --
    comparator.add("Hello World, good job!");
	comparator.add("Hello Java, good task!");
	comparator.compare();
            
	ArrayList<String> equals = comparator.equals.get();
	ArrayList<String[]> diffs = comparator.diffs.get();

**Produce:**

equals = ['Hello', ', good' , '!']
diffs = [['World', 'Java'], ['job', 'task']]