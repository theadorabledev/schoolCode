class logicPuzzle:
    def __init__(self, properties):
        self.properties = {name:set(properties[name]) for name in properties}
        self.allDict = {name:{subname : {otherName:self.properties[otherName] for otherName in self.properties if otherName != name} for subname in self.properties[name]} for name in self.properties}
        self.numPossibilities = sum(len(self.properties[prop]) for prop in self.properties)/len(self.properties)
        self.possibilities = [properties.copy()] * len(properties)
        self.connected = set()
    def position(self, where, what):
        #what (group, name)
        group, name = what
        self.possibilites[where][group] = set([name])
    def connect(self, thing1a, thing2a):
        group1, thing1 = thing1a
        group2, thing2 = thing2a
        self.allDict[group1][thing1][group2] = set([thing2])
        self.allDict[group2][thing2][group1] = set([thing1])
    def followConnect(self, thing1a, thing2a):
        """ Disconnects connected items from others."""
        group1, thing1 = thing1a
        group2, thing2 = thing2a
        for thing in self.allDict[group1]:
            if thing != thing1:
                self.allDict[group1][thing][group2].discard(thing2)
        for thing in self.allDict[group2]:
            if thing != thing2:
                self.allDict[group2][thing][group1].discard(thing1)
    def disconnect(sef, thing1a, thing2a):
        pass
    def update(self):
        change = True
        while change:
            change = False
            for group in self.allDict:
                for thing in self.allDict[group]:
                    for subGroup in self.allDict[group][thing]:
                        thing2 = self.allDict[group][thing][subGroup]
                        if len(self.allDict[group][thing][subGroup]) == 1 and frozenset([thing, thing2]) not in self.connected:
                            self.followConnect((group, thing), (subGroup, str(self.allDict[group][thing][subGroup])))
                            change = True
                            self.connected.add(frozenset([thing, thing2]))
                        elif len(self.allDict[group][thing][subGroup]) == 0:
                            print "messed up"
                        
def main():
    l = logicPuzzle( {'Name':['Bill','Karl','Joy'],'Politics':['Republican','Democrat','Libertarian'],'Course':['Biology','AI','APUSH']})
    l.connect(("Name", "Bill"), ("Course", "APUSH"))
    #print l.properties
    print l.allDict
    l.update()
    print l.allDict
    #print l.numPossibilities
    #print l.possibilities


main()
