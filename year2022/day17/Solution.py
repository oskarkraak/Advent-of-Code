def part1(chamber_width: int, rock_count: int, jets: str):
    chamber = [line_of_char(chamber_width, "#")]
    empty_line = line_of_char(chamber_width, ".")
    highest_rock = 0
    rock_index = 0
    jet_index = 0
    for i in range(rock_count):
        rock = rocks[rock_index]
        spawn_y = highest_rock + 3 + height(rock)
        for j in range(len(chamber), spawn_y + 1):
            chamber.append(empty_line)
        rock_position = [2, spawn_y]
        resting = False
        while not resting:
            jet = jets[jet_index]
            rock_position = apply_jet(chamber, jet, rock, rock_position)
            jet_index = (jet_index + 1) % len(jets)
            if can_fall_down(chamber, rock, rock_position):
                rock_position = fall_down(rock_position)
            else:
                resting = True
                put_rock(chamber, rock, rock_position)
                highest_rock = max(highest_rock, rock_position[1])
        rock_index = (rock_index + 1) % len(rocks)
    print("Part 1:", highest_rock)


def apply_jet(chamber, jet, rock, rock_position):
    assert jet == ">" or jet == "<"
    if jet == ">":
        direction = (1, 0)
    if jet == "<":
        direction = (-1, 0)
    if can_move(chamber, rock, rock_position, direction):
        return move(rock_position, direction)
    else:
        return rock_position


def can_fall_down(chamber, rock, rock_position) -> bool:
    direction = (0, -1)
    return can_move(chamber, rock, rock_position, direction)


def fall_down(rock_position):
    direction = (0, -1)
    return move(rock_position, direction)


def can_move(chamber, rock, rock_top_left, direction) -> bool:
    new_x_rock_top_left = rock_top_left[0] + direction[0]
    new_y_rock_top_left = rock_top_left[1] + direction[1]
    for i in range(len(rock)):
        for j in range(len(rock[i])):
            x = new_x_rock_top_left + j
            y = new_y_rock_top_left - i
            if y < 0 or y >= len(chamber):
                return False
            if x < 0 or x >= len(chamber[y]):
                return False
            if rock[i][j] == "#" and chamber[y][x] == "#":
                return False
    return True


def move(position, direction):
    new_x = position[0] + direction[0]
    new_y = position[1] + direction[1]
    return new_x, new_y


def put_rock(chamber, rock, rock_position):
    for i in range(len(rock)):
        for j in range(len(rock[i])):
            x = rock_position[0] + j
            y = rock_position[1] - i
            if rock[i][j] == "#":
                line = chamber[y]
                chamber[y] = line[:x] + "#" + line[x + 1:]


def height(rock) -> int:
    return len(rock)


def width(rock) -> int:
    return len(rock[0])


def print_chamber(chamber: list[str]):
    for i in range(len(chamber), 0, -1):
        print("|", chamber[i - 1], "|", sep="")
    print()


def line_of_char(length: int, char: str) -> str:
    string = ""
    for i in range(length):
        string += char
    return string


rocks = [
    [
        "####"
    ],
    [
        ".#.",
        "###",
        ".#."
    ],
    [
        "..#",
        "..#",
        "###"
    ],
    [
        "#",
        "#",
        "#",
        "#"
    ],
    [
        "##",
        "##"
    ],
]
file = open("input.txt").readlines()
part1(7, 2022, file[0].strip())
