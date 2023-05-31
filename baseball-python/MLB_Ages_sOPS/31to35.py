import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import string as str

df = pd.read_csv(r'C:\Users\ninte\Downloads\mlb_age_sops.csv', encoding = "ISO-8859-1")

for i in range(30):
    df['colors'] = ['red' if int(x) < 100 else 'green' for x in df['thirtyone_five']]

plt.figure(figsize=(14,10), dpi=80)

plt.hlines(y=df.team, xmin=100, xmax=df.thirtyone_five, color=df.colors, alpha=0.4, linewidth=8)

plt.gca().set(ylabel='Teams', xlabel='sOPS+')

plt.title('Performance of Players Age 31 to 35', fontdict={
    'size': 20})

plt.grid(linestyle='--', alpha=0.5)

plt.show()