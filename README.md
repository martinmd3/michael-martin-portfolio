# michael-martin-portfolio
# U.S. Port Activity Dashboard

An interactive Power BI dashboard analyzing monthly container throughput across nine major U.S. ports from 2020–2023, built to understand how port activity recovered and diverged in the years following the pandemic.

## The Question

How did container volumes at major U.S. ports recover after the 2020 disruption, and why did some ports grow faster than others?

## Data & Methodology

- **Source:** U.S. Department of Transportation, Bureau of Transportation Statistics (BTS)
- **Scope:** Monthly TEU (twenty-foot equivalent unit) throughput for nine U.S. container ports, January 2020–August 2023
- **Tools:** Data cleaned and reshaped in Power Query; dashboard and DAX measures built in Power BI
- **Note:** Year-over-year growth comparisons use complete calendar years (2020–2022) to avoid partial-year distortion

## Key Findings

- **Sharp recovery, then a plateau.** Total monthly throughput across all nine ports fell sharply in early 2020, recovered through the remainder of the year, and rose about 16.5% from 2020 to 2021. 2022 volume was essentially flat versus 2021.
- **Growth was uneven across ports.** From 2020–2022, Houston (+32.3%) and the Port of Virginia (+31.6%) grew far faster than the largest ports, while Oakland declined (-5.0%).
- **Scale and growth rate are different things.** Los Angeles and Long Beach handled the most total volume by far, but they were not the fastest-growing — a reminder that "biggest" and "fastest-growing" answer different questions.
- **A plausible explanation for the shift:** 2021–2022 is well documented as a period of severe congestion at the Port of Los Angeles and Port of Long Beach. Contemporary industry reporting described cargo being redirected toward East and Gulf Coast ports — including Houston, Virginia, and Charleston — to avoid West Coast delays. This offers a plausible (not proven) explanation for why Houston and Virginia outpaced the West Coast's largest ports during this window.

## Dashboard

![Dashboard overview](screenshots/dashboard-overview.png)

The dashboard includes a port filter, an aggregate volume trend, a total-throughput ranking by port, a year-over-year growth comparison, and a full monthly time series by port.

## Tools Used

Power BI · Power Query · DAX

---
*Michael Martin | [LinkedIn](https://www.linkedin.com/in/michael-martin-917b47327/)*
