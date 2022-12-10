def part1():
    sum_of_signal_strengths = 0
    cycles = (20, 60, 100, 140, 180, 220)
    cycle = 0
    x = 1
    for op in file:
        op = op.strip()
        cycle += 1
        sum_of_signal_strengths += get_signal_strength(cycle, x, cycles)
        if op.split(" ")[0] == "addx":
            cycle += 1
            sum_of_signal_strengths += get_signal_strength(cycle, x, cycles)
            x += int(op.split(" ")[1])
    print("Part 1:", sum_of_signal_strengths)


def get_signal_strength(cycle, x, cycles):
    signal_strength = 0
    if cycle in cycles:
        signal_strength = cycle * x
    return signal_strength


def part2():
    width = 40
    height = 6
    screen = init_screen(width, height)
    sprite_pos = 1
    line_number = 0
    for crt_pos in range(width * height):
        if abs(crt_pos % width - sprite_pos) <= 1:
            screen[int(crt_pos / width)][int(crt_pos % width)] = "#"
        line = file[line_number].strip()
        if line == "noop":
            line_number += 1
        elif line.split(" ")[0] == "addx":
            file[line_number] = line.split(" ")[1]
        else:
            sprite_pos += int(line)
            line_number += 1
    print("Part 2:")
    print_screen(screen)


def init_screen(width, height):
    screen = []
    for i in range(height):
        screen.append([])
        for j in range(width):
            screen[i].append(".")
    return screen


def print_screen(screen):
    for i in range(len(screen)):
        for j in range(len(screen[i])):
            print(screen[i][j], end=" ")
        print(end="\n")


file = open("input.txt").readlines()
part1()
part2()
