def part1():
    tail_visited = set()
    head = [0, 0]
    tail = [0, 0]
    for move in file:
        move = move.strip()
        # Move head
        direction = directions[move.split(" ")[0]]
        move_amount = int(move.split(" ")[1])
        for i in range(move_amount):
            head[0] += direction[0]
            head[1] += direction[1]
            # Move tail
            tail = follow_head(tail, head)
            tail_visited.add(tuple(tail))
    print("Part 1:", len(tail_visited))


def part2():
    rope_length = 10
    tail_visited = set()
    knots = []
    for i in range(rope_length):
        knots.append([0, 0])
    head = knots[0]
    for move in file:
        move = move.strip()
        # Move head
        direction = directions[move.split(" ")[0]]
        move_amount = int(move.split(" ")[1])
        for i in range(move_amount):
            head[0] += direction[0]
            head[1] += direction[1]
            # Move following knots
            for j in range(1, len(knots)):
                knots[j] = follow_head(knots[j], knots[j - 1])
            tail_visited.add(tuple(knots[-1]))
    print("Part 2:", len(tail_visited))


def follow_head(tail, head):
    for i in range(len(tail)):
        if abs(head[i] - tail[i]) > 1:
            if head[i] > tail[i]:
                tail[i] += 1
            else:
                tail[i] -= 1
            i -= 1
            if abs(head[i] - tail[i]) > 0:
                if head[i] > tail[i]:
                    tail[i] += 1
                else:
                    tail[i] -= 1
            i += 1
    return tail


file = open("input.txt").readlines()
directions = {"R": (0, 1), "U": (1, 0), "L": (0, -1), "D": (-1, 0)}
part1()
part2()
