/**
 * @author Sherlok Chan
 * 2025-05-29
 */
public class Character {
	// attributes
	private boolean isMale;
	private boolean wearingGlasses;
	private boolean hasMoustache;
	private boolean wearingBunnyEars;
	private boolean isBald;
	private boolean hasBlondHair;
	private boolean hasBlackHair;
	private boolean hasBrownHair;
	private boolean wearingBandana;
	private boolean wearingHairBand;
	private boolean wearingEarrings;
	private boolean wearingTie;
	private boolean wearingNecklace;
	private boolean mouthOpen;


	/**
	 * This constructor will initialize all the attributes that a Character object contain
     * 
	 * @param isMale
	 * @param wearingGlasses
	 * @param hasMoustache
	 * @param wearingBunnyEars
	 * @param isBald
	 * @param hasBlondHair
	 * @param hasBlackHair
	 * @param hasBrownHair
	 * @param wearingBandana
	 * @param wearingHairBand
	 * @param wearingEarrings
	 * @param wearingTie
	 * @param wearingNecklace
	 * @param mouthOpen
	 */
	public Character(
			boolean isMale,
			boolean wearingGlasses,
			boolean hasMoustache,
			boolean wearingBunnyEars,
			boolean isBald,
			boolean hasBlondHair,
			boolean hasBlackHair,
			boolean hasBrownHair,
			boolean wearingBandana,
			boolean wearingHairBand,
			boolean wearingEarrings,
			boolean wearingTie,
			boolean wearingNecklace,
			boolean mouthOpen) 
	{
		this.isMale = isMale;
		this.wearingGlasses = wearingGlasses;
		this.hasMoustache = hasMoustache;
		this.wearingBunnyEars = wearingBunnyEars;
		this.isBald = isBald;
		this.hasBlondHair = hasBlondHair;
		this.hasBlackHair = hasBlackHair;
		this.hasBrownHair = hasBrownHair;
		this.wearingBandana = wearingBandana;
		this.wearingHairBand = wearingHairBand;
		this.wearingEarrings = wearingEarrings;
		this.wearingTie = wearingTie;
		this.wearingNecklace = wearingNecklace;
		this.mouthOpen = mouthOpen;
	}

	/**
	 * This method will check the character's attribute against the question,
	 * returning a boolean value
	 * 
	 * @param question
	 * @return boolean
	 */
	public boolean isMatch(String question) {
		if (question.equals("Is your character a male?")) {
			return isMale;
		}
		else if (question.equals("Is your character wearing glasses?")) {
			return wearingGlasses;
		}
		else if (question.equals("Does your character have a moustache?")) {
			return hasMoustache;
		}
		else if (question.equals("Is your character wearing bunny ears?")) {
			return wearingBunnyEars;
		}
		else if (question.equals("Is your character bald?")) {
			return isBald;
		}
		else if (question.equals("Does you character have blond hair?")) {
			return hasBlondHair;
		}
		else if (question.equals("Does you character have black hair?")) {
			return hasBlackHair;
		}
		else if (question.equals("Does you character have brown hair?")) {
			return hasBrownHair;
		}
		else if (question.equals("Is your character wearing a bandana?")) {
			return wearingBandana;
		}
		else if (question.equals("Is your character wearing a band in the hair?")) {
			return wearingHairBand;
		}
		else if (question.equals("Is your character wearing earrings?")) {
			return wearingEarrings;
		}
		else if (question.equals("Is your character wearing a tie?")) {
			return wearingTie;
		}
		else if (question.equals("Is your character wearing a necklace?")) {
			return wearingNecklace;
		}
		else if (question.equals("Does your chracter have mouth open?")) {
			return mouthOpen;
		}
		else {
			return false;
		}
	}



	/* getter methods */
	public boolean isMale() {
		return isMale;
	}

	public boolean isWearingGlasses() {
		return wearingGlasses;
	}

	public boolean hasMoustache() {
		return hasMoustache;
	}

	public boolean isWearingBunnyEars() {
		return wearingBunnyEars;
	}

	public boolean isBald() {
		return isBald;
	}

	public boolean hasBlondHair() {
		return hasBlondHair;
	}

	public boolean hasBlackHair() {
		return hasBlackHair;
	}

	public boolean hasBrownHair() {
		return hasBrownHair;
	}

	public boolean isWearingBandana() {
		return wearingBandana;
	}

	public boolean isWearingHairBand() {
		return wearingHairBand;
	}

	public boolean isWearingEarrings() {
		return wearingEarrings;
	}

	public boolean isWearingTie() {
		return wearingTie;
	}

	public boolean isWearingNecklace() {
		return wearingNecklace;
	}

	public boolean isMouthOpen() {
		return mouthOpen;
	}
}