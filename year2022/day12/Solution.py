def part1():
    # Start from "S", search "E"
    start = s_pos
    end = file[e_pos[0]][e_pos[1]]
    steps = distance(start, end, is_height_in_range_part1)
    print("Part 1:", steps)


def is_height_in_range_part1(old_height, new_height):
    return ord(new_height) <= ord(old_height) + 1


def part2():
    # Start from "E", search "a"
    start = e_pos
    end = "a"
    steps = distance(start, end, is_height_in_range_part2)
    print("Part 2:", steps)


def is_height_in_range_part2(old_height, new_height):
    return ord(new_height) + 1 >= ord(old_height)


def find_char(lines, char):
    for y in range(len(lines)):
        for x in range(len(lines[y])):
            if lines[y][x] == char:
                return y, x


def distance(start_coordinates, end_char, is_height_in_range):
    steps = 0
    # BFS
    queue = [start_coordinates]
    current_step_length = 1
    visited = {start_coordinates}
    while len(queue) != 0:
        pos = queue.pop(0)
        current_step_length -= 1
        height = file[pos[0]][pos[1]]
        if height == end_char:
            break
        for direction in directions:
            y = pos[0] + direction[0]
            x = pos[1] + direction[1]
            in_bounds = (not x < 0) and (not y < 0) and (not y >= len(file)) and (not x >= len(file[y]))
            if in_bounds:
                new_height = file[y][x]
                if is_height_in_range(height, new_height):
                    new_pos = (y, x)
                    if new_pos not in visited:
                        visited.add(new_pos)
                        queue.append(new_pos)
        if current_step_length == 0:
            steps += 1
            current_step_length = len(queue)
    return steps


file = open("input.txt").readlines()
s_pos = find_char(file, "S")
e_pos = find_char(file, "E")
for i in range(len(file)):
    file[i] = file[i].strip()
    file[i] = file[i].replace("S", "a")
    file[i] = file[i].replace("E", chr(ord("z") + 1))
directions = ((1, 0), (0, 1), (-1, 0), (0, -1))
part1()
part2()
