id_list = []
weight_list = []
coll_time_list = []
value_list = []


def quick(lst):                     # Quick sort algorithm
    n = len(lst)
    if n == 1 or n == 0:
        return lst
    pivot = lst[0]
    left = []
    right = []
    for i in range(1,n):
        if lst[i] <= pivot:
            left.append(lst[i])
        if lst[i]>pivot:
            right.append(lst[i])
    return quick(left) + lst[:1] + quick(right)


def weighted_calculator(remaining_limit, i):          # This only considers weight limit.
    if i == n:
        return 0, []
    taken_value = 0
    taken_list = []
    if remaining_limit >= weight_list[i]:
        taken_value, taken_list = weighted_calculator(remaining_limit-weight_list[i], i+1)
        taken_value += value_list[i]
        taken_list.append(id_list[i])
    wasnt_taken_value, wasnt_taken_list = weighted_calculator(remaining_limit, i+1)
    if taken_value > wasnt_taken_value:
        return taken_value, taken_list
    else:
        return wasnt_taken_value, wasnt_taken_list


def timed_calculator(remaining_limit, i):          # This only considers time limit.
    if i == n:
        return 0, []
    taken_value = 0
    taken_list = []
    if remaining_limit >= coll_time_list[i]:
        taken_value, taken_list = timed_calculator(remaining_limit-coll_time_list[i], i+1)
        taken_value += value_list[i]
        taken_list.append(id_list[i])
    wasnt_taken_value, wasnt_taken_list = timed_calculator(remaining_limit, i+1)

    if taken_value > wasnt_taken_value:
        return taken_value, taken_list
    else:
        return wasnt_taken_value, wasnt_taken_list


def overall_calculator(remaining_weight, remaining_time, i):          # This considers botj weight and time limit.
    if i == n:
        return 0, []
    taken_value = 0
    taken_list = []
    if remaining_weight >= weight_list[i] and remaining_time >= coll_time_list[i]:
        taken_value, taken_list = overall_calculator(remaining_weight-weight_list[i], remaining_time-coll_time_list[i], i+1)
        taken_value += value_list[i]
        taken_list.append(id_list[i])
    wasnt_taken_value, wasnt_taken_list = overall_calculator(remaining_weight, remaining_time, i+1)
    if taken_value > wasnt_taken_value:
        return taken_value, taken_list
    else:
        return wasnt_taken_value, wasnt_taken_list


f = open('crime_scene.txt')
all = f.read().split()
weight_limit = int(all[0])
all.pop(0)
time_limit = int(all[0])
all.pop(0)
n = int(all[0])
all.pop(0)
for i in range(n):
    id_list.append(int(all[0]))
    all.pop(0)
    weight_list.append(int(all[0]))
    all.pop(0)
    coll_time_list.append(int(all[0]))
    all.pop(0)
    value_list.append(int(all[0]))
    all.pop(0)
part_1_1, part_1_list = weighted_calculator(weight_limit, 0)
part_1_list = quick(part_1_list)
part_2_1, part_2_list = timed_calculator(time_limit, 0)
part_2_list = quick(part_2_list)
part_3_1, part_3_list = overall_calculator(weight_limit, time_limit, 0)
part_3_list = quick(part_3_list)
p1 = open('solution_part1.txt', 'w')
p1.write(str(part_1_1) + '\n')
for e in part_1_list:
    p1.write(str(e) + ' ')
p2 = open('solution_part2.txt', 'w')
p2.write(str(part_2_1) + '\n')
for e in part_2_list:
    p2.write(str(e) + ' ')
p3 = open('solution_part3.txt', 'w')
p3.write(str(part_3_1) + '\n')
for e in part_3_list:
    p3.write(str(e) + ' ')
p1.close()
p2.close()
p3.close()
f.close()