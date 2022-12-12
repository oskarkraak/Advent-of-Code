def part1():
    steps = 0
    # BFS
    queue = [start]
    current_step_length = 1
    visited = {start}
    while len(queue) != 0:
        pos = queue.pop(0)
        current_step_length -= 1
        height = file[pos[0]][pos[1]]
        if height == "E":
            break
        for direction in directions:
            y = pos[0] + direction[0]
            x = pos[1] + direction[1]
            in_bounds = (not x < 0) and (not y < 0) and (not y >= len(file)) and (not x >= len(file[y]))
            if in_bounds:
                new_height = file[y][x]
                if height == "S":
                    height = "a"
                if new_height == "E":
                    new_height = chr(ord("z") + 1)
                too_high = ord(new_height) > ord(height) + 1
                if not too_high:
                    new_pos = (y, x)
                    if new_pos not in visited:
                        visited.add(new_pos)
                        queue.append(new_pos)
        if current_step_length == 0:
            steps += 1
            current_step_length = len(queue)
    print("Part 1:", steps)


def find_start(lines):
    for i in range(len(lines)):
        for j in range(len(lines[i])):
            if lines[i][j] == "S":
                return i, j
    print("No start found")


file = open("input.txt").readlines()
for i in range(len(file)):
    file[i] = file[i].strip()
directions = ((1, 0), (0, 1), (-1, 0), (0, -1))
start = find_start(file)
part1()
