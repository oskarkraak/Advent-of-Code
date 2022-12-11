def part1(monkeys):
    num_rounds = 20
    relief_factor = 3
    monkey_activity = get_monkey_activity(monkeys, num_rounds, relief_factor)
    monkey_business = get_monkey_business(monkey_activity)
    print("Part 1:", monkey_business)


def part2(monkeys):
    num_rounds = 10000
    monkey_activity = get_monkey_activity(monkeys, num_rounds)
    monkey_business = get_monkey_business(monkey_activity)
    print("Part 2:", monkey_business)


def init_monkeys():
    monkeys = []
    monkey_index = -1
    for line in file:
        line = line.strip()
        if line.startswith("Monkey"):
            monkeys.append([])
            monkey_index += 1
        if line.startswith("Starting items"):
            line = line.removeprefix("Starting items: ")
            monkeys[monkey_index].append(line.split(", "))
        if line.startswith("Operation"):
            monkeys[monkey_index].append(line.split(" ")[4])
            monkeys[monkey_index].append(line.split(" ")[5])
        if line.startswith("Test"):
            monkeys[monkey_index].append(int(line.split(" ")[-1]))
        if line.startswith("If true"):
            monkeys[monkey_index].append(int(line.split(" ")[-1]))
        if line.startswith("If false"):
            monkeys[monkey_index].append(int(line.split(" ")[-1]))
    return monkeys


def get_monkey_activity(monkeys, num_rounds, relief_factor=1):
    monkey_activity = []
    for _ in monkeys:
        monkey_activity.append(0)
    product_of_divisors = 1
    for monkey in monkeys:
        product_of_divisors *= monkey[3]
    for keep_away_round in range(num_rounds):
        for i in range(len(monkeys)):
            monkey = monkeys[i]
            items = monkey[0]
            operation = monkey[1]
            operation_value = monkey[2]
            divisible_by = monkey[3]
            if_true = monkey[4]
            if_false = monkey[5]
            for item in items:
                item = int(item)
                # Inspect
                monkey_activity[i] += 1
                if operation_value == "old":
                    operation_number = item
                else:
                    operation_number = int(operation_value)
                if operation == "+":
                    item += operation_number
                if operation == "*":
                    item *= operation_number
                # Divide by relief_factor
                item = item // relief_factor
                # To keep the numbers low:
                item = item % product_of_divisors
                # Test and throw
                if item % divisible_by == 0:
                    monkeys[if_true][0].append(item)
                else:
                    monkeys[if_false][0].append(item)
            monkey[0] = []
    return monkey_activity


def get_monkey_business(monkey_activity):
    max_two = [0, 0]
    for i in monkey_activity:
        if i > max_two[0]:
            max_two[0] = i
        if max_two[0] > max_two[1]:
            temp = max_two[1]
            max_two[1] = max_two[0]
            max_two[0] = temp
    return max_two[0] * max_two[1]


file = open("input.txt").readlines()
part1(init_monkeys())
part2(init_monkeys())
