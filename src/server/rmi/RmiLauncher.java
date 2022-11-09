package server.rmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RmiLauncher implements Runnable{

    private final Integer port;

    public RmiLauncher(Integer port) {
       this.port = port;
    }

    /**
     * Launching the rmi registry on the specified port*/
    @Override
    public void run() {
            String rmiCmd = "rmiregistry" + " " + port.toString();
            try {
                System.out.println("Testing 1");
                Process process
                        = Runtime.getRuntime().exec(rmiCmd);

                System.out.println("Testing 2");


                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Testing 3");

                    System.out.println(
                            "**************************** The Output is ******************************");

                    System.exit(0);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

