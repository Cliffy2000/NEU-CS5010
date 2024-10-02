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
    private TextParser textParser;

    private boolean flag_hasRum = false;
    private boolean flag_onShip = false;
    private boolean flag_sailUp = false;
    private boolean flag_holdWheel = false;
    private boolean flag_stage1Complete = false;

    private boolean flag_gaveRum = false;
    private boolean flag_adjustSail = false;
    private boolean flag_adjustHeading = false;
    private int flag_shipSpeed = 1;
    private int flag_travelDistance = 0;
    private boolean flag_stage2Complete = false;

    private boolean flag_stage3Complete = false;

    public Model() {
        this.gameStageIndex = 0;
        this.stages = new ArrayList<>();
        initStages();
        this.textParser = new TextParser(new HashSet<>(this.stages.get(2).getVerbs()), this.stages.get(0).getNouns().keySet());
    }

    public TextParser getTextParser() {
        return this.textParser;
    }

    public Stage getCurrentStage() {
        return this.stages.get(this.gameStageIndex);
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
                put("sail", "Your ship is sailing towards the destination.");
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
        Set<String> stage3Verbs = new HashSet<>(Arrays.asList("look", "walk"));
        Map<String, Map<String, String>> stage3Nouns = new HashMap<>() {{
            put("beach", new HashMap<>(){{
                put("look", "You see a trail of footprints, a suspiciously moist patch of sand, and a coconut.");
                put("walk", "You see a trail of footprints, a suspiciously moist patch of sand, and a coconut.");
            }});
        }};
        Stage stage3 = new Stage("Island", stage3Verbs, stage3Nouns);
        this.stages.add(stage3);
    }

    public String getResponse(String noun, String verb) {
        return this.stages.get(this.gameStageIndex).nouns.get(noun).get(verb);
    }

    public void action(String noun, String verb) {
        // switch function that directs to specific stage
        switch (gameStageIndex) {
            case 0:
                actionStage1(this.stages.get(0), noun, verb);
            case 1:
                actionStage2(this.stages.get(1), noun, verb);
            case 2:
                actionStage3(this.stages.get(2), noun, verb);
            default:
                // Error catching default block
                break;
        }
    }

    public void actionStage1(Stage stage, String noun, String verb) {
        // Manages the effects verbs have on the stage
        switch (noun) {
            case "around":
                if (verb.equals("look") && !flag_onShip) {
                    stage.addVerbToNoun("rum", "take", "You decided to bring the rum with you onto the ship.");
                }
                break;
            case "ship":
                if (verb.equals("board")) {
                    flag_onShip = true;
                    // major change to the stage as boarding the ship would change the surroundings of the player
                    stage.removeNoun("around");
                    stage.removeNoun("ship");
                    stage.addVerbToNoun("around", "look", "You stand on the deck in front of the wheel, your crew on your side and the sails tied neatly together.");
                    stage.addVerbToNoun("wheel", "hold", "You feel the strength of the ship coming to you as you hold the wheel firmly.");
                    stage.addVerbToNoun("sails", "ready", "You order the crew to ready the sails");
                }
                break;
            case "crew":
                // added switch case for clarity, talking to crew on this stage does not affect game flow
                break;
            case "rum":
                if (verb.equals("take") && !flag_onShip) {
                    this.flag_hasRum = true;
                    stage.removeNoun("rum");
                    stage.removeNoun("around");
                    stage.addVerbToNoun("around", "look", "You see your ship docked nearby.");
                    this.stages.get(1).addVerbToNoun("rum", "distribute", "You gave the rum to the crew members, lifting everyone's spirits. ");
                    this.stages.get(1).addVerbToNoun("rum", "drink", "You drank the rum, it tastes good. ");
                }
                break;
            case "wheel":
                this.flag_holdWheel = true;
                break;
            case "sails":
                this.flag_sailUp = true;
                stage.removeNoun("around");
                stage.addVerbToNoun("around", "look", "You stand on the deck of your ship, the crew at their posts ready for departure.");
                break;
        }

        // Completed the stage
        if (flag_holdWheel && flag_sailUp) {
            this.flag_stage1Complete = true;
        }
    }

    public void actionStage2(Stage stage, String noun, String verb) {
        switch (noun) {
            case "rum":
                if (verb.equals("distribute")) {
                    this.flag_gaveRum = true;
                    this.flag_shipSpeed += 1;
                    stage.removeNoun("rum");
                    break;
                } else if (verb.equals("drink")) {
                    stage.removeNoun("rum");
                    break;
                }
            case "map":
                if (verb.equals("check")) {
                    if (!this.flag_adjustHeading) {
                        this.flag_adjustHeading = true;
                        this.flag_shipSpeed += 1;
                    }
                }
                break;
            case "sails":
                if (verb.equals("adjust")) {
                    if (!this.flag_adjustSail) {
                        this.flag_adjustSail = true;
                        this.flag_shipSpeed += 1;
                    }
                }
                break;
            case "ship":
                if (verb.equals("sail")) {
                    if (this.flag_shipSpeed == 1) {
                        this.flag_travelDistance += 2;
                    } else if (this.flag_shipSpeed < 4) {
                        this.flag_travelDistance += 5;
                    } else if (this.flag_shipSpeed == 4) {
                        this.flag_travelDistance += 10;
                    }
                }
                break;
        }

        // travel enough distance to reach the next stage
        if (this.flag_travelDistance >= 20) {
            this.flag_stage2Complete = true;
        }
    }

    public void actionStage3(Stage stage, String noun, String verb) {
        switch (noun) {
            case "beach":
                if (verb.equals("look") || verb.equals("walk")) {
                    stage.removeNoun("beach");
                    stage.addVerbToNoun("footprints", "follow", "You follow the footsteps to see a bag half buried in the ground.");
                    stage.addVerbToNoun("sand", "dig", "You dig a hole only to get a strong rotting smell and a pile of rotten fish. ");
                    stage.addVerbToNoun("coconut", "pick", "You picked up a coconut, but it seems perfectly normal.");
                    stage.addVerbToNoun("coconut", "break", "You break a coconut and drank the coconut water");
                }
                break;
            case "footprints":
                if (verb.equals("follow")) {
                    stage.removeNoun("footprints");
                    stage.addVerbToNoun("bag", "dig", "You dig up the bag and find a bag of ancient gold coins.");
                }
                break;
            case "sand":
                if (verb.equals("dig")) {
                    stage.removeNoun("sand");
                }
                break;
            case "coconut":
                break;
            case "bag":
                if (verb.equals("dig")) {
                    this.flag_stage3Complete = true;
                    stage.removeNoun("bag");
                }
                break;
        }
    }

    public int getProgression() {
        if (this.gameStageIndex == 0 && this.flag_stage1Complete) {
            // move from stage 1 to 2
            this.gameStageIndex = 1;
            return 0;
        } else if (this.gameStageIndex == 1 && this.flag_stage2Complete) {
            // move from stage 2 to 3
            this.gameStageIndex = 2;
            return 1;
        } else if (this.gameStageIndex == 2 && this.flag_stage3Complete) {
            // game completed case
            return 2;
        } else {
            // default pass case
            return -1;
        }
    }

    public String[] parseInput(String userInput) {
        return this.textParser.parse(userInput);
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

        public void addVerbToNoun(String noun, String verb, String response) {
            if (this.nouns.containsKey((noun))) {
                // Add or overwrite if the noun exists
                this.nouns.get(noun).put(verb, response);
            } else {
                // create new noun before adding the verb that should be added to it
                Map<String, String> verbResponse = new HashMap<>();
                verbResponse.put(verb, response);
                this.nouns.put(noun, verbResponse);
            }
            // Make sure the verb is included in the overall stage verb list
            addVerb(verb);
        }

        public String getResponse(String noun, String verb) {
            if (this.nouns.containsKey(noun) && this.nouns.get(noun).containsKey(verb)) {
                return this.nouns.get(noun).get(verb);
            }
            // This message should not appear as the parsed text shouldn't fail the if statement
            // Mainly as precaution
            return "Invalid interaction!";
        }

        public void removeNoun(String noun) {
            if (this.nouns.containsKey(noun)) {
                Set<String> verbsToRemove = this.nouns.get(noun).keySet();
                nouns.remove(noun);

                // check if the remaining verbs are used by any other nouns
                for (String verb : verbsToRemove) {
                    boolean verbIsUsed = false;
                    // iterate through the remaining nouns
                    for (Map<String, String> verbMap : this.nouns.values()) {
                        if (verbMap.containsKey(verb)) {
                            verbIsUsed = true;
                            break;
                        }
                    }

                    // Remove the verb if it is not used elsewhere
                    if (!verbIsUsed) {
                        this.verbs.remove(verb);
                    }
                }
            }
        }

        public void removeVerbFromNoun(String noun, String verb) {
            if (this.nouns.containsKey(noun)) {
                Map<String, String> verbMap = this.nouns.get(noun);
                verbMap.remove(verb);

                // makes sure the remaining nouns all have actual available actions
                if (verbMap.isEmpty()) {
                    removeNoun(noun);
                }
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
            String[] words = userInput.toLowerCase().split(" ");
            String userVerb = "not found";
            String userNoun = "not found";

            // Loop through words and remove only trailing periods
            for (int i = 0; i < words.length; i++) {
                if (words[i].endsWith(".")) {
                    words[i] = words[i].substring(0, words[i].length() - 1);
                }
            }

            for (String word : words) {
                if (this.nouns.contains(word)) {
                    userNoun = word;
                    break;
                }
            }

            for (String word : words) {
                if (this.verbs.contains(word)) {
                    userVerb = word;
                    break;
                }
            }

            return new String[] {userVerb, userNoun};
        }
    }
}
