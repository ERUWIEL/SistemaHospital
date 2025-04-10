package persistencias;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author xdlol
 */
public class MyObjectOutputStream extends ObjectOutputStream{
    public MyObjectOutputStream(OutputStream out) throws IOException{
        super(out);
    }
    
    @Override
    public void writeStreamHeader()throws IOException{
        reset();
    }
}
