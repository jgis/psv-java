# PSV Java Implementation

This is an implemenation of the [PSV Spec](https://github.com/jgis/psv-spec).

## Reading a PSV stream

Reading works like any other InputStream. Just use the readRow() method to read a row from the stream.

```java
import jgis.psv.PSVInputStream;
:
FileInputStream fis = new FileInputStream(...); // or some other input stream
PSVInputStream in = new PSVInputStream(fis);
String[] row;
while((row = in.readRow()) != null) {
	// do something with the row    
}
```

## Writing a PSV stream

Use the writeRow() method to write string arrays to the PSV stream.

```java
import jgis.psv.PSVOutputStream;
:
FileOutputStream fos = new FileOutputStream(...);
PSVOutputStream out = new PSVOutputStream(fos);

out.writeRow("this", "is", "the", "first", "row");
out.writeRow(new String[] { "this", "is", "the", "second", "row"});
out.writeRow("this", "is", "the", "third", "row", "with more fields");

out.close();
```