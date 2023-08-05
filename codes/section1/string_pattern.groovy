myFairString = 'The rain in Spain stays mainly in the plain!'

BOUNDS = /\b/
rhyme = /$BOUNDS\w*ain$BOUNDS/
found = ''
myFairString.eachMatch(rhyme) {match ->
    found += match[0] + ' '
}
assert found == 'r S p '

found = ''
(myFairString =~ rhyme).each { match ->
    found += match + ' '
}
assert found == 'rain Spain plain '

cloze = myFairString.replaceAll(rhyme){it-'ain' + '___'}
assert cloze == 'The r___ in Sp___ stays mainly in the pl___!'