public class HolidayLights {
    public static void main(String argv[]) {

        // Task 1: Creating the Light Chains and link them.
        LightChain stringOfLights = new LightChain();

        // Task 2 Traversing the Light Chains.
        System.out.println("After the task 1 (Creating the Light Chains), we have");
        stringOfLights.traverseLightChain();

        // Task 3: Inserting a New Light.
        stringOfLights.insertAfter(LightChain.ORANGE, LightChain.BLUE);

        System.out.println("After the Task 3 (Inserting a Orange after Blue), we have");
        stringOfLights.traverseLightChain();

        // Task 4: Replacing a Light.
        stringOfLights.replaceAll(LightChain.ORANGE, LightChain.PURPLE);

        System.out.println("After the task 5 (Replacing all orange with purple), we have");
        stringOfLights.traverseLightChain();
    }
}
