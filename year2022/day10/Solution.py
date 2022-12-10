def part1():
    sum_of_signal_strengths = 0
    cycle = 0
    x = 1
    for op in file:
        op = op.strip()
        cycle += 1
        sum_of_signal_strengths += get_signal_strength(cycle, x)
        if op.split(" ")[0] == "addx":
            cycle += 1
            sum_of_signal_strengths += get_signal_strength(cycle, x)
            x += int(op.split(" ")[1])
    print("Part 1:", sum_of_signal_strengths)


def get_signal_strength(cycle, x):
    signal_strength = 0
    if cycle in cycles:
        signal_strength = cycle * x
    return signal_strength


file = open("input.txt").readlines()
cycles = (20, 60, 100, 140, 180, 220)
part1()
