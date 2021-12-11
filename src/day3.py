import sys


def part1(lines):
    arr = []
    row = 0
    for line in lines:
        arr.append([int(a) for a in line])
        row += 1

    t_arr = [*zip(*arr)]

    gamma = ''
    epsilon = ''
    for r in t_arr:
        v = max(set(r), key=r.count)
        gamma += str(v)
        epsilon += str(v ^ 1)

    #print(arr[0])
    #print(t_arr[0])
    print(gamma)
    #print(epsilon)
    #print(int(gamma,2))
    #print(int(epsilon,2))
    #print(int(epsilon,2)*int(gamma,2))
        

def part2(lines):
    depth = 0
    horizontal = 0
    aim = 0
    for line in lines:
        if 'forward' in line:
            n = int(line.split('forward ')[1])
            horizontal += n
            depth += aim*n
        elif 'down' in line:
            n = int(line.split('down ')[1])
            aim += n
        elif 'up' in line:
            n = int(line.split('up ')[1])
            aim -= n
    
    print("horizontal: {}".format(horizontal))
    print("depth: {}".format(depth))
    print("aim: {}".format(depth))
    
    print("A: {}".format(horizontal * depth))

if __name__ == "__main__":
    filename = sys.argv[1]

    with open(filename, 'r') as f:
        lines = [line.rstrip('\n') for line in f]
    part1(lines)
