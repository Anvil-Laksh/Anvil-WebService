package live.anvill.ws.workspace;

public class ExceptionTest  {
    public static void main(String[] args) {
        try
        {
            int i;
            i = 5/-1;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally {
            System.out.println("rdtdtdtrdtr");
        }

    }
}
