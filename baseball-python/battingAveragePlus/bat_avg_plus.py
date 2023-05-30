import pandas as pd
import numpy as np

df = pd.read_csv(r'C:\Users\ninte\Downloads\bref_2022_avg.csv', encoding = "ISO-8859-1")

avg_arr = [0 for i in range(131)]

for x in range(131):
    batAvg = df.at[x, 'BA']
    lgBatAvg = df.at[x, 'lgBA']
    batAvgPlus = batAvg/lgBatAvg

    avg_arr[x] = 100 * batAvgPlus

df['AVG+'] = avg_arr
df['AVG+'] = df['AVG+'].round()


#print(df.at[0, 'AVG+'])

df.sort_values(by=['AVG+'], ascending=False, inplace=True)
df = df.reset_index(drop = True)

for x in range(131):
    print(df.at[x, 'Name'] + " " + str(df.at[x, 'AVG+']))



