def part1():
    packets = []
    for line in file:
        packets.append(line.strip())
    sum_of_pairs_in_right_order = 0
    for i in range(0, len(packets), 3):
        packet1 = packets[i]
        packet2 = packets[i + 1]
        if is_pair_in_right_order(packet1, packet2):
            sum_of_pairs_in_right_order += int(i / 3) + 1
    print("Part 1:", sum_of_pairs_in_right_order)


def part2():
    divider_packets = ["[[2]]", "[[6]]"]
    packets = []
    for packet in divider_packets:
        packets.append(packet)
    for line in file:
        if line.strip() != "":
            packets.append(line.strip())
    bubble_sort(packets, is_pair_in_right_order)
    decoder_key = 1
    for i in range(len(packets)):
        if packets[i] in divider_packets:
            decoder_key *= i + 1
    print("Part 2:", decoder_key)


def is_pair_in_right_order(left: str, right: str) -> bool or None:
    result = None
    left = remove_outer_brackets(left)
    right = remove_outer_brackets(right)
    left_values = left.split(",")
    left_index = 0
    right_values = right.split(",")
    right_index = 0
    while result is None:
        left_out_of_items = left_index >= len(left_values) or left == ""
        right_out_of_items = right_index >= len(right_values) or right == ""
        if left_out_of_items and right_out_of_items:
            return None
        elif left_out_of_items:
            result = True
        elif right_out_of_items:
            result = False
        else:
            left_value = left_values[left_index]
            right_value = right_values[right_index]
            if left_value[0] == "[" or right_value[0] == "[":
                if left_value[0] == "[":
                    closing_bracket_index = get_closing_bracket_index(left_values, left_index)
                    left_value = substring(left_values, left_index, closing_bracket_index)
                    left_index = closing_bracket_index
                if right_value[0] == "[":
                    closing_bracket_index = get_closing_bracket_index(right_values, right_index)
                    right_value = substring(right_values, right_index, closing_bracket_index)
                    right_index = closing_bracket_index
                result = is_pair_in_right_order(left_value, right_value)
            else:
                left_num = int(left_value)
                right_num = int(right_value)
                if left_num < right_num:
                    result = True
                elif left_num > right_num:
                    result = False
            left_index += 1
            right_index += 1
    return result


def remove_outer_brackets(string: str) -> str:
    if len(string) == 0:
        return ""
    if string[0] == "[":
        string = string[1:]
    if string[-1] == "]":
        string = string[0:-1]
    return string


def get_closing_bracket_index(values: list[str], opening_bracket_index: int) -> int:
    stack = 0
    for i in range(opening_bracket_index, len(values)):
        for c in values[i]:
            if c == "[":
                stack += 1
            if c == "]":
                stack -= 1
            if stack == 0:
                return i


def substring(values: list[str], opening_bracket_index: int, closing_bracket_index: int) -> str:
    result = ""
    for i in range(opening_bracket_index, closing_bracket_index + 1):
        for c in values[i]:
            result += c
        result += ","
    return result.strip(",")


def bubble_sort(packets: list[str], is_lower):
    for i in range(0, len(packets) - 1):
        for j in range(0, len(packets) - i - 1):
            if is_lower(packets[j + 1], packets[j]):
                temp = packets[j]
                packets[j] = packets[j + 1]
                packets[j + 1] = temp


file = open("input.txt").readlines()
part1()
part2()
