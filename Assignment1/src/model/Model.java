/*
Citations:
    Help on ArrayLists: https://www.w3schools.com/java/java_arraylist.asp
    Help on Java lists: https://www.geeksforgeeks.org/list-interface-java-examples/

 */

package model;

import java.util.*;

public class Model {
    private int gameStageIndex;
    private List<Stage> stages;
    private int gameStep;
    private TextParser textParser;

    public Model() {
        this.gameStageIndex = 0;
        this.stages = new ArrayList<>();
        initStages();
        this.gameStep = 0;
        this.textParser = new TextParser(new HashSet<>(stages.get(0).getVerbs()), stages.get(0).getNouns().keySet());
    }

    private void initStages() {
        // This is the initialization of the stages. The verbs and nouns that get added in this funciton are those are available as soon as the player enters the stage.

        // Stage 1 (index 0)
        // Leaving the dock
        Set<String> stage1Verbs = new HashSet<>(Arrays.asList("board", "talk", "look"));
        Map<String, Map<String, String>> stage1Nouns = new HashMap<>(){{
            put("ship", new HashMap<>() {{
                put("look", "You see your ship on the dock.");
                put("board", "You board your ship.");
            }});
            put("crew", new HashMap<>() {{
                put("talk", "You greet and chat with your crew.");
            }});
            put("around", new HashMap<>() {{
                put("look", "You are at a busy seaport, your ship is docked and there is also a crate of rum nearby that someone probably forgot to bring with them. ");
            }});
        }};

        Stage stage1 = new Stage("Dock", stage1Verbs, stage1Nouns);
        this.stages.add(stage1);

        // Stage 2 (index 1)
        // Sailing on the sea
        Set<String> stage2Verbs = new HashSet<>(Arrays.asList("sail", "check", "adjust"));
        Map<String, Map<String, String>> stage2Nouns = new HashMap<>() {{
            put("ship", new HashMap<>() {{
                put("sail-slow", "Your ship is sailing towards the destination.");
                put("sail-medium", "Your ship is sailing steadily along the wind towards your destination.");
                put("sail-fast", "Your are approaching your destination rapidly.");
            }});
            put("map", new HashMap<>() {{
                put("check", "You checked the map to gain a more precise heading.");
            }});
            put("sails", new HashMap<>() {{
                put("adjust", "You adjusted the sails to catch the wind better.");
            }});
        }};

        Stage stage2 = new Stage("Sea", stage2Verbs, stage2Nouns);
        this.stages.add(stage2);

        // Stage 3 (index 2)
        // Finding the treasure on the island
        Set<String> stage3Verbs = new HashSet<>(Arrays.asList("look"));
        Map<String, Map<String, String>> stage3Nouns = new HashMap<>() {{
            put("around", new HashMap<>(){{
                put("around", "You see a trail of footsteps, a suspiciously moist patch of sand, and a coconut.");
            }});
        }};
        Stage stage3 = new Stage("Island", stage3Verbs, stage3Nouns);
        this.stages.add(stage3);
    }

    public class Stage {
        private String name;
        private Set<String> verbs;
        private Map<String, Map<String, String>> nouns;

        public Stage(String name, Set<String> verbs, Map<String, Map<String, String>> nouns) {
            this.name = name;
            this.verbs = verbs;
            this.nouns = nouns;
        }

        public String getName() {
            return this.name;
        }

        public Set<String> getVerbs() {
            return this.verbs;
        }

        public void addVerb(String verb) {
            this.verbs.add(verb);
        }

        public Map<String, Map<String, String>> getNouns() {
            return this.nouns;
        }

        public void addVerbtoNoun(String noun, String verb, String response) {
            if (this.nouns.containsKey((noun))) {
                // Add or overwrite if the noun exists
                nouns.get(noun).put(verb, response);
            } else {
                // create new noun before adding the verb that should be added to it
                Map<String, String> verbResponse = new HashMap<>();
                verbResponse.put(verb, response);
                nouns.put(noun, verbResponse);
            }
            // Make sure the verb is included in the overall stage verb list
            addVerb(verb);
        }

        public String getResponse(String noun, String verb) {
            if (nouns.containsKey(noun) && nouns.get(noun).containsKey(verb)) {
                return nouns.get(noun).get(verb);
            }
            // This message should not appear as the parsed text shouldn't fail the if statement
            // Mainly as precaution
            return "Invalid interaction!";
        }

        public void removeNoun(String noun) {
            nouns.remove(noun);
        }

        public void removeVerbFromNoun(String noun, String verb) {
            if (nouns.containsKey(noun)) {
                nouns.get(noun).remove(verb);
            }
        }
    }

    public class TextParser {
        private Set<String> verbs;
        private Set<String> nouns;

        public TextParser(Set<String> verbs, Set<String> nouns) {
            this.verbs = verbs;
            this.nouns = nouns;
        }

        public void setVerbs(Set<String> verbs) {
            this.verbs = verbs;
        }

        public void setNouns(Set<String> nouns) {
            this.nouns = nouns;
        }

        public String[] parse(String userInput) {
            // TODO: Remove trailing punctuations
            String[] words = userInput.toLowerCase().split(" ");
            String userVerb = "not found";
            String userNoun = "not found";

            for (String word : words) {
                if (nouns.contains(word)) {
                    userNoun = word;
                    break;
                }
            }

            for (String word : words) {
                if (verbs.contains(word)) {
                    userVerb = word;
                    break;
                }
            }

            return new String[] {userVerb, userNoun};
        }
    }
}
