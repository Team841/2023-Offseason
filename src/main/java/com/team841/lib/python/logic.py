
possible  = [None, 2, 3]

def check(a, b):
    if (a == None or b == None) or not a == b:
        print("false")
        return
    print("true")

def inverseCheck(a, b):
    if a == b != None:
        print("true")
        return
    print("false")

# check(possible[0], possible[1])
# check(possible[0], possible[0])
# check(possible[2], possible[2])
# check(possible[2], possible[1])

inverseCheck(possible[0], possible[1])
inverseCheck(possible[0], possible[0])
inverseCheck(possible[2], possible[2])
inverseCheck(possible[2], possible[1])


