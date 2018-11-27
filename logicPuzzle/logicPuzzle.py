class logicPuzzle:
    def __init__(self, properties):
        self.properties = {name:set(properties[name]) for name in properties}
        self.allDict = {name:{subname : {otherName:self.properties[otherName] for otherName in self.properties if otherName != name} for subname in self.properties[name]} for name in self.properties}
        self.numPossibilities = sum(len(self.properties[prop]) for prop in self.properties)/len(self.properties)
        self.possibilities = [properties.copy()] * len(properties)
    def position(self, where, what):
        #what (group, name)
        group, name = what
        self.possibilites[where][group] = set([name])
def main():
    l = logicPuzzle( {'Name':['Bill','Karl','Joy'],'Politics':['Republican','Democrat','Libertarian'],'Course':['Biology','AI','APUSH']})
    #print l.properties
    print l.allDict
    print l.numPossibilities
    print l.possibilities


main()
