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

    }

    private void initStages() {
        // Stage 1 (index 0)
        
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
        private List<String> verbs;
        private List<String> nouns;

        public TextParser(List<String> verbs, List<String> nouns) {
            this.verbs = verbs;
            this.nouns = nouns;
        }

        public void setVerbs(List<String> verbs) {
            this.verbs = verbs;
        }

        public void setNouns(List<String> nouns) {
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
