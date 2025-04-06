import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Dono extends Gerente{
    public Dono() {
        super(0, "Admin", 3);
        isDono = true;
    }
}