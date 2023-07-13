import pandas as pd
import numpy as np
from sklearn.datasets import load_iris
import matplotlib.pyplot as plt

df = pd.read_csv(r'C:\Users\ninte\Downloads\woba_xwobacon.csv', encoding = "ISO-8859-1")


fig, ax = plt.subplots()

ax.axhline(y=0.385, color="r")
plt.scatter(df.woba, df.xwobacon, c="navy")

plt.show()
