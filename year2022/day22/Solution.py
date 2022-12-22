def part1():
    board_map = get_board_map(file)
    rest_path = get_path(file)
    facing = 0
    row = 0
    col = board_map[row].find(".")
    while rest_path != '':
        instruction = get_next_instruction(rest_path)
        rest_path = rest_path.removeprefix(instruction)
        if instruction == "R":
            facing = (facing + 1) % 4
        elif instruction == "L":
            facing = (facing - 1) % 4
        else:
            number_of_tiles = int(instruction)
            direction = directions[facing]
            new_pos = move(row, col, number_of_tiles, direction, board_map)
            row = new_pos[0]
            col = new_pos[1]
    final_password = 1000 * (row + 1) + 4 * (col + 1) + facing
    print("Part 1:", final_password)


def get_next_instruction(path: str) -> str:
    if path[0] == "R" or path[0] == "L":
        return path[0]
    next_instruction = ""
    for char in path:
        if char.isnumeric():
            next_instruction += char
        else:
            break
    return next_instruction


def move(row: int, col: int, number_of_tiles: int, direction: tuple[int], board_map: list[str]) -> tuple[int, int]:
    for i in range(number_of_tiles):
        next_row = row + direction[1]
        next_col = col + direction[0]
        if is_out_of_bounds(next_row, next_col, board_map):
            while not is_out_of_bounds(next_row - direction[1], next_col - direction[0], board_map):
                next_row -= direction[1]
                next_col -= direction[0]
        if board_map[next_row][next_col] == ".":
            row = next_row
            col = next_col
        else:
            break
    return row, col


def is_out_of_bounds(row: int, col: int, board_map: list[str]):
    too_far_up = row < 0
    too_far_down = row >= len(board_map)
    if too_far_up or too_far_down:
        return True
    too_far_left = col < 0
    too_far_right = col >= len(board_map[row])
    if too_far_left or too_far_right:
        return True
    empty = board_map[row][col] == " "
    return empty


def get_board_map(file: list[str]) -> list[str]:
    board_map = list()
    for line in file[:-2]:
        board_map.append(line.removesuffix("\n"))
    return board_map


def get_path(file: list[str]) -> str:
    return file[-1].strip()


file = open("input.txt").readlines()
directions = ((1, 0), (0, 1), (-1, 0), (0, -1))
part1()
