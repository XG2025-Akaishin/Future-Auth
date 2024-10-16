package me.akaishin.auth.hwid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class Hwid {
    //private static String field_864 = System.getProperty("os.name").toUpperCase();

    // Variable que almacena el nombre del sistema operativo
    private static String field_864 = System.getProperty("os.name").toUpperCase();

    /**
     * Método que obtiene la información de la placa base
     * @return La información de la placa base
     * @throws IOException 
     */
    private static String method_1932() throws IOException {
        // Comprueba si el sistema operativo es Windows o Mac
        if (field_864.contains("WIN")) {
            // Ejecuta un comando del sistema para obtener la información de la placa base
            Process process = Runtime.getRuntime().exec("wmic baseboard get product,Manufacturer,version,serialnumber");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            scanner.nextLine();
            scanner.nextLine();
            String string = scanner.nextLine();
            scanner.close();
            return string;
        } else if (field_864.contains("MAC")) {
            // Ejecuta un comando del sistema para obtener la información de la placa base
            Process process = Runtime.getRuntime().exec("system_profiler SPHardwareDataType");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            int n = 1;
            int n2 = 1;
            while (true) {
                if (n > 16) break;
                scanner.nextLine();
                n = ++n2;
            }
            String string = scanner.nextLine();
            scanner.close();
            return string;
        }
        return "Error";
    }

    /**
     * Método que obtiene la información del controlador de video
     * @return La información del controlador de video
     * @throws IOException 
     */
    private static String method_1933() throws IOException {
        // Comprueba si el sistema operativo es Windows o Mac
        if (field_864.contains("WIN")) {
            // Ejecuta un comando del sistema para obtener la información del controlador de video
            Process process = Runtime.getRuntime().exec("wmic path Win32_VideoController get Description,PNPDeviceID");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            scanner.nextLine();
            scanner.nextLine();
            String string = scanner.nextLine();
            scanner.close();
            return string;
        } else if (field_864.contains("MAC")) {
            // Ejecuta un comando del sistema para obtener la información del controlador de video
            Process process = Runtime.getRuntime().exec("system_profiler SPDisplaysDataType");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            int n = 1;
            int n2 = 1;
            while (true) {
                if (n > 4) break;
                scanner.nextLine();
                n = ++n2;
            }
            String string = scanner.nextLine();
            scanner.close();
            return string;
        }
        return "Error";
    }

    /**
     * Método que obtiene el UUID del sistema
     * @return El UUID del sistema
     */
    private static String method_1934() {
        try {
            if (field_864.contains("WIN")) {
                // Ejecuta un comando del sistema para obtener el UUID
                String[] stringArray = new String[4];
                stringArray[0] = "wmic";
                stringArray[1] = "csproduct";
                stringArray[2] = "get";
                stringArray[3] = "uuid";
                Process process = Runtime.getRuntime().exec(stringArray);
                process.getOutputStream().close();
                Scanner scanner = new Scanner(process.getInputStream());
                scanner.nextLine();
                scanner.nextLine();
                String string = scanner.nextLine();
                scanner.close();
                return string;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "Error";
    }

    /**
     * Método que obtiene la información del sistema
     * @return La información del sistema
     * @throws IOException 
     * @throws InterruptedException 
     */
    public static String method_1935() throws IOException, InterruptedException {
        // Llama a los métodos anteriores para obtener la información del sistema
        String[] stringArray = new String[7];
        stringArray[0] = System.getenv("PROCESSOR_IDENTIFIER");
        stringArray[1] = System.getenv("NUMBER_OF_PROCESSORS");
        stringArray[2] = Hwid.method_1932();
        stringArray[3] = Hwid.method_1933();
        stringArray[4] = Hwid.method_1934();
        stringArray[5] = Hwid.method_1937();
        stringArray[6] = Hwid.method_1936();
        // Concatena la información en una sola cadena
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringArray) {
            stringBuilder.append(string);
        }
        // Comprueba si el sistema es una máquina virtual (VMware) y sale si es así
        if (stringBuilder.toString().contains("VMware")) {
            //FutureMessage.mostrarMensaje("Error!", "Máquina virtual detectada! No se puede ejecutar Future en una máquina virtual", 0);
            try {
                Method method = Class.forName("java.lang.Shutdown").getDeclaredMethod("exit", Integer.TYPE);
                method.setAccessible(true);
                method.invoke(null, 0);
            } catch (Exception exception) {
                throw new RuntimeException("Error al cargar! Por favor, publique en los foros con el código de error \"0x38F\" para obtener ayuda.");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Método que obtiene la información de la memoria
     * @return La información de la memoria
     * @throws IOException 
     * @throws InterruptedException 
     */
    private static String method_1936() throws IOException, InterruptedException {
        // Comprueba si el sistema operativo es Windows o Mac
        if (field_864.contains("WIN")) {
            // Ejecuta un comando del sistema para obtener la información de la memoria
            String[] stringArray = new String[4];
            stringArray[0] = "wmic";
            stringArray[1] = "memorychip";
            stringArray[2] = "get";
            stringArray[3] = "serialnumber";
            Process process = Runtime.getRuntime().exec(stringArray);
            process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string);
            }
            return stringBuilder.substring(stringBuilder.toString().lastIndexOf("r") + 1).trim();
        } else if (field_864.contains("MAC")) {
            // Ejecuta un comando del sistema para obtener la información de la memoria
            Process process = Runtime.getRuntime().exec("system_profiler SPMemoryDataType");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            int n = 1;
            int n2 = 1;
            while (true) {
                if (n > 9) break;
                scanner.nextLine();
                n = ++n2;
            }
            String string = scanner.nextLine();
            scanner.close();
            return string;
        }
        return "Error";
    }

    /**
     * Método que obtiene la información del disco
     * @return La información del disco
     * @throws IOException 
     * @throws InterruptedException 
     */
    private static String method_1937() throws IOException, InterruptedException {
        // Comprueba si el sistema operativo es Windows, Mac o Linux
        if (field_864.contains("WIN")) {
            // Ejecuta un comando del sistema para obtener la información del disco
            Process process = Runtime.getRuntime().exec("wmic volume get driveletter,serialnumber");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            scanner.nextLine();
            HashMap<String, String> hashMap = new HashMap<String, String>();
            while (scanner.hasNext()) {
                String[] stringArray = scanner.nextLine().replaceAll(" ", "").split(":");
                if (stringArray.length != 2) continue;
                hashMap.put(stringArray[0].toLowerCase(), stringArray[1]);
            }
            scanner.close();
            return hashMap.get("c");
        } else if (field_864.contains("MAC")) {
            // Ejecuta un comando del sistema para obtener la información del disco
            Process process = Runtime.getRuntime().exec("system_profiler SPSerialATADataType");
            process.getOutputStream().close();
            Scanner scanner = new Scanner(process.getInputStream());
            int n = 1;
            int n2 = 1;
            while (true) {
                if (n > 16) break;
                scanner.nextLine();
                n = ++n2;
            }
            String string = scanner.nextLine();
            scanner.close();
            return string;
        } else if (field_864.contains("LINUX")) {
            // Ejecuta un comando del sistema para obtener la información del disco
            String string = "/sbin/udevadm info --query=property --name=sda";
            String[] stringArray = new String[3];
            stringArray[0] = "/bin/sh";
            stringArray[1] = "-c";
            stringArray[2] = string;
            Process process = Runtime.getRuntime().exec(stringArray);
            process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String string2;
            while ((string2 = bufferedReader.readLine()) != null) {
                if (string2.contains("ID_SERIAL_SHORT")) {
                    stringBuilder.append(string2);
                }
            }
            return stringBuilder.toString().substring(stringBuilder.toString().indexOf("=") + 1);
        }
        return "Error";
    }
}
