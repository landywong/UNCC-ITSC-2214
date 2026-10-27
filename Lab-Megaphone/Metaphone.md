# Metaphone.md

## Instructions

Metaphone is an algorithm to replace common sounding letters or letter patterns such that similarly sounding words are grouped together. Note, there are implementations of metaphone algorithms on the web, this is a slightly simplified version, so don't go googling it because it might not work correctly for you.

These rules drop or substitute common English patterns based on how they sound, not how they’re spelled.

Steps:

Step 1: Drop silent starting letters
"kn", "gn", "pn", "ae", "wr"

Step 2: Collapse similar sounding letters
ph -> f
ck -> k
gh -> ""

Step 3: replace some letters for others to simplify processing
q -> k
x -> ks
z -> s

Step 4: Replace c depending on following letter
ce, ci, cy -> s
all others c_ -> k
c at end of word -> k

Step 5: Drop all vowels (aeiou) except when vowel is the first char

## Testing

dictionary.txt has 45k words that is used in the main program. The table below has 25 words randomly selected that you can use to test your 


### All of these have metaphone of prs
pairs, pares, paris, pars, parse, parsi, pearce, pears, peers, perez, persia, peruse, pierce, piers, piraeus, pores, porous, pours, praise, price, prize, pros, prose, purse, pursue

### All of these have metaphone of ls
lace, laos, laughs, lease, lees, lice, lies, lisa, lise, liz, loci, lois, loose, los, lose, louis, louisa, louise, louse, lucia, luis

