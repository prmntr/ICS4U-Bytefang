/**
 * @Author Sherlok Chan
 *         2025-05-29
 */
public class Character {
    // attributes
    private String name;
    private boolean hasGlasses;
    private boolean facialHair; // musache, beard, none
    private boolean hasBunny;
    private String hairColour;
    private String accessories; // bandana, band in the hair, earrings, tie, necklace, glasses
    private boolean mouthOpen;
    private String gender;
    private String race; // white, black

    /**
     * constructor, will initialize all the attributes a Character has
     * 
     * @param name
     * @param hairColor
     * @param eyeColor
     * @param hasGlasses
     * @param hasHat
     * @param hasBeard
     * @param gender
     * @param race
     */
    public Character(String name, String hairColor, String eyeColor, boolean hasGlasses, boolean hasHat,
            boolean hasBeard, String gender, String race) {
        this.name = name;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.hasGlasses = hasGlasses;
        this.hasHat = hasHat;
        this.hasBeard = hasBeard;
        this.gender = gender;
        this.race = race;
    }

    /**
     * This method will check the character's attribute against the question,
     * returning a boolean value
     * 
     * @param question
     * @return boolean
     */
    public boolean isMatch(String question) {
        if (question.equalsIgnoreCase("Is your character male")) {
            return gender != null && gender.equalsIgnoreCase("male");
        } else if (question.equalsIgnoreCase("Is your character female")) {
            return gender != null && gender.equalsIgnoreCase("female");
        } else if (question.startsWith("Does your character have ") && question.endsWith(" hair")) {
            String qHairColour = question
                    .substring("Does your character have ".length(), question.length() - " hair".length()).trim();
            return hairColour != null && hairColour.equalsIgnoreCase(qHairColour);
        } else if (question.equalsIgnoreCase("Does your character wear glasses")) {
            return hasGlasses;
        } else if (question.equalsIgnoreCase("Does your character have a mustache")) {
            return facialHair && accessories != null && accessories.toLowerCase().contains("mustache");
        } else if (question.equalsIgnoreCase("Does your character have a beard")) {
            return facialHair && accessories != null && accessories.toLowerCase().contains("beard");
        } else if (question.equalsIgnoreCase("Does your character have no facial hair")) {
            return !facialHair;
        } else if (question.equalsIgnoreCase("Does your character have a bunny")) {
            return hasBunny;
        } else if (question.equalsIgnoreCase("Does your character wear a bandana")) {
            return accessories != null && accessories.toLowerCase().contains("bandana");
        } else if (question.equalsIgnoreCase("Does your character wear a band in the hair")) {
            return accessories != null && accessories.toLowerCase().contains("band in the hair");
        } else if (question.equalsIgnoreCase("Does your character wear earrings")) {
            return accessories != null && accessories.toLowerCase().contains("earrings");
        } else if (question.equalsIgnoreCase("Does your character wear a tie")) {
            return accessories != null && accessories.toLowerCase().contains("tie");
        } else if (question.equalsIgnoreCase("Does your character wear a necklace")) {
            return accessories != null && accessories.toLowerCase().contains("necklace");
        } else if (question.equalsIgnoreCase("Does your character wear glasses")) {
            return hasGlasses;
        } else if (question.equalsIgnoreCase("Is your character's mouth open")) {
            return mouthOpen;
        } else if (question.startsWith("Is your character of race ")) {
            String qRace = question.substring("Is your character of race ".length()).trim();
            return race != null && race.equalsIgnoreCase(qRace);
        }
        return false;
    }

    /* getter methods */
    public String getName() {
        return name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean getHasGlasses() {
        return hasGlasses;
    }

    public boolean getHasHat() {
        return hasHat;
    }

    public boolean getHasBeard() {
        return hasBeard;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

}
